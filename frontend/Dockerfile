FROM node:16.16-alpine3.15

WORKDIR /app

ADD . .

RUN yarn install
RUN yarn build

EXPOSE 3000

CMD ["yarn", "start"]
