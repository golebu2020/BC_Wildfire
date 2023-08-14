# Use an official Node.js runtime as the base image
FROM node:14-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install app dependencies
RUN npm install

# Copy all files from the current directory to the working directory
COPY . .

# Build the Vue.js app
RUN npm run build

# Expose a port on which the app will run (usually 80 for HTTP)
EXPOSE 80

# Define the command to run your app using a simple HTTP server
CMD ["npm", "run", "serve"]

# # FROM node:14.17.3-buster as build
# FROM node:14-alpine as build

# WORKDIR /code

# COPY package.json package.json
# COPY package-lock.json package-lock.json
# # RUN npm ci --production
# COPY . .


# RUN npm run serve
# # RUN npm install

# # NGINX Web server
# FROM nginx:1.12-alpine as prod

# COPY --from=build /code/build /usr/share/nginx/html

# EXPOSE 80

# CMD [ "nginx", "-g", "daemon off;"]