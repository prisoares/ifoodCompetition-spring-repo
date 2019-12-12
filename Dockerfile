
# cria um container tendo como a base a imagem de java 8
FROM openjdk:8

# define o lugar onde serão copiados os arquivos, e criadas novas pastas
WORKDIR /usr/src/api

# copia a folder gradle do nosso projeto local
COPY gradle/ gradle/

# copia os arquivos do gradle do nosso projeto local
COPY build.gradle settings.gradle gradlew ./

# busca a versao do gradle do container que estamos criando só pra logar e podermos validar ela
RUN ./gradlew --version

# Copia o código, caso tenha alguma atualizacao
COPY src/ src/

# Testa, e controi um artefato novo no container e
RUN ./gradlew build bootRun
