![Azure Functions Logo](https://raw.githubusercontent.com/Azure/azure-functions-cli/master/src/Azure.Functions.Cli/npm/assets/azure-functions-logo-color-raster.png)

|Branch|Status|
|---|---|
|dev|[![Build Status](https://dev.azure.com/azfunc/Azure%20Functions/_apis/build/status/Azure.azure-functions-host?branchName=dev)](https://dev.azure.com/azfunc/Azure%20Functions/_build/latest?definitionId=37&branchName=dev)|
|release/4.x|[![Build Status](https://dev.azure.com/azfunc/Azure%20Functions/_apis/build/status/Azure.azure-functions-host?branchName=release%2F4.x)](https://dev.azure.com/azfunc/Azure%20Functions/_build/latest?definitionId=37&branchName=release%2F4.x)|
|v3.x|[![Build Status](https://dev.azure.com/azfunc/Azure%20Functions/_apis/build/status/Azure.azure-functions-host?branchName=v3.x)](https://dev.azure.com/azfunc/Azure%20Functions/_build/latest?definitionId=37&branchName=v3.x)
|release/3.0|[![Build Status](https://dev.azure.com/azfunc/Azure%20Functions/_apis/build/status/Azure.azure-functions-host?branchName=release%2F3.0)](https://dev.azure.com/azfunc/Azure%20Functions/_build/latest?definitionId=37&branchName=release%2F3.0)|
|v2.x|[![Build Status](https://dev.azure.com/azfunc/Azure%20Functions/_apis/build/status/Azure.azure-functions-host?branchName=v2.x)](https://dev.azure.com/azfunc/Azure%20Functions/_build/latest?definitionId=37&branchName=v2.x)|
|release/2.0|[![Build Status](https://dev.azure.com/azfunc/Azure%20Functions/_apis/build/status/Azure.azure-functions-host?branchName=release%2F2.0)](https://dev.azure.com/azfunc/Azure%20Functions/_build/latest?definitionId=37&branchName=release%2F2.0)|
|v1.x|[![Build status](https://ci.appveyor.com/api/projects/status/a6j46j1tawdfs3js?svg=true&branch=v1.x)](https://ci.appveyor.com/project/appsvc/azure-webjobs-sdk-script-y8o14?branch=v1.x)|

Example Azure Functions App in Java
===

This repo contains code for the runtime host used by the [Azure Functions](https://docs.microsoft.com/en-us/azure/azure-functions/) service. The Azure Functions runtime builds upon the [Azure WebJobs SDK](https://github.com/Azure/azure-webjobs-sdk) to provide a hosting platform for functions written in many different [languages](https://docs.microsoft.com/en-us/azure/azure-functions/supported-languages) and supporting a wide variety of [triggers and bindings](https://docs.microsoft.com/en-us/azure/azure-functions/functions-triggers-bindings?tabs=csharp#supported-bindings).

### Build
Run the function localy:
```
mvn clean package
mvn azure-functions:run
```

Deploy the function project to Azure:
```
mvn azure-functions:deploy
```

### Describe Files

##### pom.xml
``` xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
            <configuration>
                <appendAssemblyId>false</appendAssemblyId>
                <descriptors>
                    <descriptor>${basedir}/assembly.xml</descriptor>
                </descriptors>
                <finalName>${project.artifactId}</finalName>
            </configuration>
        </execution>
    </executions>
</plugin>
```
##### assembly.xml
The Assembly Plugin for Maven enables developers to combine project output into a single distributable archive that also contains dependencies, modules, site documentation, and other files.
``` xml
<assembly xmlns="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2 http://maven.apache.org/xsd/assembly-1.1.2.xsd">
    <id>zip</id>
    <includeBaseDirectory>false</includeBaseDirectory>

    <formats>
        <format>zip</format>
    </formats>
    
    <fileSets>
        <fileSet>
            <directory>${project.build.directory}/azure-functions/${functionAppName}/</directory>
            <outputDirectory>/</outputDirectory>
        </fileSet>
    </fileSets>
</assembly>
```