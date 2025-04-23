# Start from OpenJDK base image
FROM openjdk:17-jdk-slim

# Install necessary packages for AWT and X11
RUN apt-get update && apt-get install -y \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libxext6 \
    libxrandr2 \
    libx11-6 \
    libfreetype6 \
    libfontconfig1 \
    libasound2 \
    xvfb \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy the JAR file into the image
COPY target/java-car-game-1.0-SNAPSHOT.jar /app/

# Set display for Xvfb
ENV DISPLAY=:99

# Start Xvfb then run the Java GUI app
CMD ["sh", "-c", "rm -f /tmp/.X99-lock && Xvfb :99 -screen 0 1024x768x16 & java -Djava.awt.headless=false -jar java-car-game-1.0-SNAPSHOT.jar"]
