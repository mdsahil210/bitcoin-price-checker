FROM openjdk:11
EXPOSE 8080
ADD target/bitcoin-price-checker-backend.jar bitcoin-price-checker-backend.jar
ENTRYPOINT ["java","-jar","/bitcoin-price-checker-backend.jar"]