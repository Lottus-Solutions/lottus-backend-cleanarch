FROM ubuntu:latest
LABEL authors="edson"

ENTRYPOINT ["top", "-b"]