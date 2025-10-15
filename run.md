mvn clean package jetty:run



            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.54.v20240208</version>
                <configuration>
                    <scanIntervalSeconds>3</scanIntervalSeconds>
                    <httpConnector>
                        <port>8080</port>
                    </httpConnector>
                    <webApp>
                        <contextPath>/AuroraJewelry</contextPath>
                    </webApp>
                </configuration>
            </plugin>