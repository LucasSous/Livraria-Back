FROM openjdk:11
ARG JAR_FILE=out/artifacts/apirest_jar/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080:8080
ENV TZ 'America/Fortaleza'
RUN echo $TZ > /etc/timezone
CMD (sed -i 's/${SERVER_DB}/'"${SERVER_DB}"'/' /app/appsettings.Production.json && sed -i 's/${SERVER_PORT}/'"${SERVER_PORT}"'/' /app/appsettings.Production.json && sed -i 's/${DATA_BASE}/'"${DATA_BASE}"'/' /app/appsettings.Production.json && sed -i 's/${USER_DB}/'"${USER_DB}"'/' /app/appsettings.Production.json && sed -i 's/${PASSWORD_DB}/'"${PASSWORD_DB}"'/' /app/appsettings.Production.json && dotnet LinkSys.Api.dll)