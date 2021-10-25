FROM mongo
COPY polls.json /polls.json
CMD mongoimport --host mongodb --db doodle --collection polls --type json --file /polls.json --jsonArray