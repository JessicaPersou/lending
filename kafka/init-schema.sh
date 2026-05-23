#!/bin/bash

set -e

echo "Iniciando registro de schemas no Schema Registry..."

SCHEMA_REGISTRY_URL="http://schema-registry:8081"

wait_for_schema_registry() {
  echo "Aguardando Schema Registry ficar disponível..."

  until curl -s "${SCHEMA_REGISTRY_URL}/subjects" > /dev/null; do
    sleep 2
  done

  echo "Schema Registry disponível!"
}

register_schema() {
  local SCHEMA_FILE=$1
  local SUBJECT=$2

  echo ""
  echo "Registrando schema: $SUBJECT"
  echo "Arquivo: $SCHEMA_FILE"

  # Verifica se arquivo existe
  if [ ! -f "$SCHEMA_FILE" ]; then
    echo "Erro: arquivo não encontrado -> $SCHEMA_FILE"
    exit 1
  fi

  # Remove quebras de linha
  SCHEMA_CONTENT=$(tr -d '\n\r' < "$SCHEMA_FILE")

  # Escapa aspas duplas
  SCHEMA_ESCAPED=$(echo "$SCHEMA_CONTENT" | sed 's/"/\\"/g')

  # Monta payload final
  PAYLOAD="{\"schema\":\"${SCHEMA_ESCAPED}\"}"

  echo ""
  echo "Payload enviado:"
  echo "$PAYLOAD"

  RESPONSE=$(curl -s -w "\n%{http_code}" \
    -X POST \
    -H "Content-Type: application/vnd.schemaregistry.v1+json" \
    --data "$PAYLOAD" \
    "${SCHEMA_REGISTRY_URL}/subjects/${SUBJECT}/versions")

  HTTP_CODE=$(echo "$RESPONSE" | tail -1)
  BODY=$(echo "$RESPONSE" | sed '$d')

  echo ""
  echo "HTTP STATUS: $HTTP_CODE"
  echo "RESPONSE:"
  echo "$BODY"

  if [ "$HTTP_CODE" = "200" ] || [ "$HTTP_CODE" = "201" ]; then
    echo ""
    echo "Schema '$SUBJECT' registrado com sucesso!"
  else
    echo ""
    echo "Erro ao registrar schema '$SUBJECT'"
    exit 1
  fi
}

wait_for_schema_registry

# Registrar schemas
register_schema "/kafka/schemas/client-proposal.avsc" "propostas-value"

echo ""
echo "Finalizado!"