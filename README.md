# Saagie Java Client [![Build Status](https://api.travis-ci.org/PierreLeresteux/saagie-java-client.svg?branch=1.3)](https://travis-ci.org/PierreLeresteux/saagie-java-client)

Saagie Java Client is a library to easily call Saagie Manager. 

All the code is written in [Kotlin](https://kotlinlang.org) and uses an HTTPClient ([okhttp](http://square.github.io/okhttp/)) to call Saagie Manager.

### :warning: NOT READY FOR PRODUCTION NOW (and not available on Maven Central)

## Versions

For each version of the manager, we update and release the library to add new features. Be sure to use the same version of the library as your Saagie Manager (version is available in the footer)

## Output formats

We decide to deliver 3 output formats : 
 - raw (i.e. java.lang.String)
 - json (a org.json.JSONObject or a org.json.JSONArray)
 - object (using our DTO included in this library)
 
 **Why ?** 
 
Because depending your need, you can use the format you want in your application.
 
## Examples
 
 3 SaagieClient are availables :
  
  - SaagieClientRaw to deliver raw
  - SaagieClientJson to deliver json objects
  - SaagieClient to deliver DTO objects
  
You can easily create these clients using their constructor (of course just create client you need): 

```
SaagieClientRaw saagieClientRaw = new SaagieClientRaw();
SaagieClientJson saagieClientJson = new SaagieClientJson();
SaagieClient saagieClient = new SaagieClient();
```

By default, all are connected to Saagie Kumo (our cloud), if you want to connect in your Saagie Su (our appliance), your can specify the URL of the manager :

`saagieClient.setBaseURL("https://your-saagie-manager-url/api/v1");`

## Authentication 

You have to set your credentials to use the Saagie Client. 

```
saagieClient.setUser("login");
saagieClient.setPassword("password");
```

## Timeout

By default a timeout is set to 20 seconds, you can override the value (in seconds).

```saagieClient.setTimeout(10.0)``` will set the timeout at 10 seconds

## Alternatives

You can construct a SaagieClient (or SaagieClientRaw and SaagieClientJson), with all informations : 

```new SaagieClient("https://manager.prod.saagie.io/api/v1","user","password",10);```

## Usage

##### GET informations (for a platform, get job status and logs, ...)
For example, to list all the platform you have access, this is the code of a simple application : 

```
import io.saagie.client.SaagieClient;
import io.saagie.client.SaagieClientJson;
import io.saagie.client.SaagieClientRaw;
import io.saagie.client.dto.platform.Platform;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class TestSaagieClient {

    public static void main(String[] args) {
        
        
        // Create a SaagieClientRaw
        SaagieClientRaw saagieClientRaw = new SaagieClientRaw();
        saagieClientRaw.setUser("login");
        saagieClientRaw.setPassword("password");
        
        // Use the SaagieClientRaw to display all platforms
        String allPlatformsRaw = saagieClientRaw.getAllPlatforms();
        System.out.println(allPlatformsRaw);



        // Create a SaagieClientJson
        SaagieClientJson saagieClientJson = new SaagieClientJson();
        saagieClientJson.setUser("login");
        saagieClientJson.setPassword("password");
        
        // Use the SaagieClientJson to deplay all platforms
        JSONArray allPlatformsJson = saagieClientJson.getAllPlatforms();
        System.out.println(allPlatformsJson);



        // Create a SaagieClient
        SaagieClient saagieClient = new SaagieClient();
        saagieClient.setUser("login");
        saagieClient.setPassword("password");

        // Use the SaagieClientJson to deplay all platforms      
        List<Platform> allPlatformsObject = saagieClient.getAllPlatforms();
        System.out.println(allPlatformsObject);
    }
}

```

##### POST informations (create a new job, a new version, restart a job, ... )

_Documentation coming soon_

## Include it via maven/gradle


If your Saagie Manager is in version _v1.3.0 (build )_, use the SaagieJavaClient in version 1.3.X
### Maven

```
<dependency>
    <groupId>io.saagie</groupId>
    <artifactId>saagieclient</artifactId>
    <version>1.3.0</version>
</dependency>
   
```

### Gradle

```
dependencies {
    compile 'io.saagie:saagieclient:1.3.0'
}
```
