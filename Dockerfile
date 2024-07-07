FROM openjdk:11
EXPOSE 8080
ADD target/bitcoin-price-checker.jar bitcoin-price-checker.jar
ENTRYPOINT ["java","-jar","/bitcoin-price-checker.jar"]