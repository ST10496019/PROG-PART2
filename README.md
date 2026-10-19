# QuickChat – Programming Part 1 and Part 2

Java console application built for Apache NetBeans using Maven and Java 17.

## Run in Apache NetBeans
1. Open NetBeans and choose **File → Open Project**.
2. Select this folder (the folder containing `pom.xml`).
3. Right-click the project and choose **Clean and Build**.
4. Run `chatapp.Main`.

Part 1 contains registration and login validation. After successful login, Part 2 opens the QuickChat menu. Messages can be sent, disregarded, or stored in `messages.json` in the working directory. Unit tests are in `src/test/java/chatapp` and use JUnit 5.

## Run tests
In NetBeans, right-click the project and choose **Test**. With Maven installed, run `mvn test` from this directory.

## GitHub Actions
The workflow in `.github/workflows/maven.yml` runs the Maven tests on pushes and pull requests.
