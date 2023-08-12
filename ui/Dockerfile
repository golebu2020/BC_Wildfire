# FROM node:14.17.3-buster as build
FROM node:14-alpine as build

WORKDIR /ui

COPY package.json package.json
# COPY package-lock.json package-lock.json
# RUN npm ci --production
COPY . .


# RUN chmod +x /ui/node_modules/.bin/react-scripts

RUN npm run build
# RUN npm install

# NGINX Web server
FROM nginx:1.12-alpine as prod

COPY --from=build /ui/build /usr/share/nginx/html


EXPOSE 80

CMD [ "nginx", "-g", "daemon off;"]