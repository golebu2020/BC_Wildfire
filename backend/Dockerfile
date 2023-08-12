FROM python:3.9-alpine3.13

LABEL maintainer="cgolebu@gmail.com"

ENV PYTHONUNBUFFERED 1

COPY backend/requirements.txt /tmp/requirements.txt

COPY backend /app

WORKDIR /app

EXPOSE 8000

RUN python -m venv /py && \
    /py/bin/pip install --upgrade pip && \
    apk add --update --no-cache postgresql-client && \
    apk add --update --no-cache --virtual .tmp-build-deps \
        build-base postgresql-dev musl-dev && \
    /py/bin/pip install -r /tmp/requirements.txt && \
    rm -rf /tmp && \
    apk del .tmp-build-deps && \
    adduser \
        --disabled-password \
        --no-create-home \
        golebu2023

ENV PATH="/py/bin:$PATH"

USER golebu2023



