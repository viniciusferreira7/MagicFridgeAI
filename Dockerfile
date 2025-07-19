FROM bitnami/java:17-debian-12

LABEL mainteiner ="john.doe@example.com"

WORKDIR app/

COPY target/MagicFridgeAI-0.0.1-SNAPSHOT.jar /app/magic-fridge-ai.jar

ENTRYPOINT ["java", "-jar", "magic-fridge-ai.jar"]