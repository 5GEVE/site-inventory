#!/usr/bin/env bash

# This needs jq, uuidgen (from e2fsprogs)
# Remember to escape the dollar sign, the backticks (backward single quotes, ``) and the backslash
# in generate_post_data()!.

HOST=localhost
PORT=8087
SITE_ID=1 # Italy default
N=5 # Number of shippers to be generated

generate_post_data()
{
  cat <<EOF
{
  "dataShipperId": "$(uuidgen)",
  "ipAddress": "$(printf "%d.%d.%d.%d\n" "$((RANDOM % 256))" "$((RANDOM % 256))" "$((RANDOM % 256))" "$((RANDOM % 256))")",
  "username": "root",
  "password": "password",
  "configurationScript": "SCRIPT",
  "stopConfigScript": "SCRIPT",
  "metricType": "USER_DATA_RATE_DOWNLINK"
}
EOF
}

for ((i=1;i<=N;i++));
do
  printf "generating shipper %s\n" "${i}"
  SITES_LINK=$(curl --request POST \
    --url http://${HOST}:${PORT}/dataShippers \
    --header 'content-type: application/json' \
    --data "$(generate_post_data)" | jq -r ._links.site.href);

  curl --request PUT \
    --url "${SITES_LINK}" \
    --header 'content-type: text/uri-list' \
    --data http://${HOST}:${PORT}/sites/"${SITE_ID}";
done

