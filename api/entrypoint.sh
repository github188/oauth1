#!/usr/bin/env bash
set -e
chown appuser:appuser /mnt/logs
exec gosu appuser java -Djava.security.egd=file:/dev/./urandom -jar /app.jar