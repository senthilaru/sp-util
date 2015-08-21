### SP-Util
The core functionality of this tool is similar to the method toString() in a java domain object, but it scan the `@Mask`
annotation and masks the values of the confidential information.
It will scan through the object graph deeper and finds the `@Mask` annotation and manipulates the values.

**Installation**
```xml
<dependency>
    <groupId>com.immibytes</groupId>
    <artifactId>sp-utils</artifactId>
    <version>1.0.0-RELEASE</version>
</dependency>
```
**How to use**

1. Specify the annotation @Mask in the java domain object.
``` java
public class UserInfo{
   @Mask  
   private String emailId = "myemail@domain.com";
   private boolean online = true;
   private List<ContactNumber> instances = new ArrayList<>();
}
```
2. Initialize the MaskLogger and use the toString method, pass the domain object.
``` java
UserInfo instance = new UserInfo();
MaskLogger maskLogger= new MaskLogger();
String maskedString = maskLogger.toString(instance);
System.out.println(maskedString); // Print as: [emailId=XX, online=true,instances=[]]
```
Note: If you using it in the spring application, initialize using below
``` xml
<bean id="maskLogger" class="com.immibytes.util.log.MaskLogger"/>
```
