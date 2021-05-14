# Api versioning

In this example we are going to build a rest endpoint with different versions, if we hit http://localhost:8080/v1/hello we will get 'Hello from v1' and visiting another instance of the same code base (jar) with http://localhost:9090/v2/hello we will get 'Hello from v2'.
\
\
You can version your endpoints very easly with the spring prop `server.servlet.context-path` and you can also have two diffrent service implementation in the same code base using the spring annotation `@ConditionalOnProperty`.

**Using docker**
\
To build the image:
\
`docker build -t apiversioning .`
\
To run the two instances:
\
`docker run --name apiV1 --env API_VERSION=/v1 -p 8080:8080 -d solocoding/apiversioning`
\
`docker run --name apiV2 --env API_VERSION=/v2 -p 9090:8080 -d solocoding/apiversioning`
\
then hit http://localhost:8080/v1/hello and http://localhost:9090/v2/hello
\
\
**Using kubernetes**
\
To run the two instances:
\
apply the kube folder in this repo, we are using also getambassador:
\
`kubectl apply -f kube/deploymentV1.yml`
\
`kubectl apply -f kube/deploymentV2.yml`
\
then visit http://localhost/api-v1/v1/hello and http://localhost/api-v2/v2/hello
\
\
**Using kubernetes and ambassador, the devops way**
\
Worth mentioning you can replace the `server.servlet.context-path` prop with a custom one like `version` for example, in this way all per-routing work is transferred to Ambassador and spring is responsible only for the beans. And we get ride of the v1 and v2 from the urls: http://localhost/api-v1/hello
