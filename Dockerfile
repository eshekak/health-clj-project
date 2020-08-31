FROM openjdk:8-alpine as builder

RUN apk add --update nodejs curl bash

RUN curl -O https://download.clojure.org/install/linux-install-1.10.1.561.sh

RUN chmod +x linux-install-1.10.1.561.sh

RUN ./linux-install-1.10.1.561.sh

WORKDIR /app

COPY ./ ./

RUN clojure -m cljs.main -O simple -t node -d ./public -o ./public/server.js -c health-clj-project.server.index


FROM node:12-alpine as runner

WORKDIR /app

COPY --from=builder /app/public /app/public

CMD ["sh", "-c", "node ./public/server.js"]
