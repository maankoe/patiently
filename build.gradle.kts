plugins {
    id("java")
}

group = "patiently"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.assertj:assertj-core:3.23.1")
    implementation("org.mockito:mockito-core:4.8.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}