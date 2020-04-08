FROM node:lts-alpine3.10

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm ci --only=production

COPY . .

EXPOSE 80

CMD ["npm", "run", "start:prod" ]