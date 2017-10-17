# -*- coding: utf-8 -*-
import os
import json

from invoke import task
from invoke import Collection

PROJECT_DIR = os.path.abspath(os.path.dirname(__file__))
ENV_DIR = os.path.join(PROJECT_DIR, "env")
WEB_DIR = os.path.join(PROJECT_DIR, "app/web")
CONFIG_PATH = os.path.join(PROJECT_DIR, "app/res/probe/common/config.json")

class Config(object):
    instance = None

    @classmethod
    def get(cls):
        if cls.instance is None:
            with open(CONFIG_PATH) as fstream:
                cls.instance = json.load(fstream)

        return cls.instance

class PsqlConnstr(object):

    def __init__(self, cluster):
        password = ""
        if "password" in cluster:
            password = """PGPASSWORD="{}""".format(cluster["password"])

        self._connstr = "{} psql -v ON_ERROR_STOP=1 -h {} -p {} -U {} -d {}".format(
            password,
            cluster.get("host", "localhost"),
            cluster.get("port", 5432),
            cluster.get("user", "postgres"),
            cluster.get("dbname", "postgres")
        )

    def file(self, fpath):
        return "{} -f {}".format(self._connstr, fpath)

    def query(self, query):
        return "{} -c '{}'".format(self._connstr, query)

@task(name="init")
def envInit(ctx):
    ctx.run("tar xf {0}/isomorphic.tar.gz -C {1}".format(ENV_DIR, WEB_DIR))
    ctx.run("tar xf {0}/tomee.tar.gz -C {0}".format(ENV_DIR))

@task(name="create")
def dbCreate(ctx, reset=False):
    config = Config.get()
    dbSection = config["db"]
    if reset:
        terminate = """SELECT pg_terminate_backend(pid)
            FROM pg_stat_activity
            WHERE datname = '{}'
            AND pid != pg_backend_pid();""".format(
            dbSection["dbname"]
        ).replace("\n", " ")
        ctx.run("""psql -v ON_ERROR_STOP=1 -U postgres -c "{}" """.format(terminate))
        ctx.run("""psql -v ON_ERROR_STOP=1 -U postgres -c "drop database {}" """.format(dbSection["dbname"]))

    ctx.run("""psql -v ON_ERROR_STOP=1 -U postgres -c "create database {}" """.format(dbSection["dbname"]))

@task(name="init")
def dbInit(ctx):
    config = Config.get()
    psql = PsqlConnstr(config["db"])

    schemaPath = os.path.join(PROJECT_DIR, "app/db/init_db.sql")
    os.chdir(os.path.dirname(schemaPath))
    ctx.run(psql.file(schemaPath))

ns = Collection(
    Collection("env", envInit),
    Collection("db", dbCreate, dbInit)
)
