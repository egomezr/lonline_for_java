![alt text](http://lonline.dynamicloud.org/assets/img/lonline-logo.png?rand=1 "Lonline logo")

<hr>

[![Build Status](https://travis-ci.org/dynamicloud/lonline_for_java.svg?branch=master)](https://travis-ci.org/dynamicloud/lonline_for_java)
![alt text](https://img.shields.io/badge/api%20version-0.0.1-brightgreen.svg "")

#For Java 

Lonline allows you to log your program's execution into the cloud to avoid server access and disk space usage.

Lonline provides 6 levels of logging and 2 methods to execute reports.  Lonline is a logger to log your program through a storing service called Dynamicloud.  With Dynamicloud we can store data dynamically and very easy, Lonline allows you to set more features and this way log more than only text, level and program trace.

**This documentation has the following content:**

1. [Dependencies](#dependencies)
2. [Settings](#settings)
  1. [Installation](#installation)
  2. [Dynamicloud account](#dynamicloud-account)
3. [How to use](#how-to-use)
  1. [Log using the six levels](#log-using-the-six-levels)
  2. [Additional data](#additional-data)
  3. [Execute reports](#execute-reports)

#Dependencies
**Lonline has two main dependencies:** [dynamicloud](https://github.com/dynamicloud/dynamicloud_java_api "Dynamicloud Java API") and its dependencies and log4j.

#Installation
This mvn command will generate the Lonline jar file

```bash
mvn package
```

If you want to add to your Maven project, just add this dependency:
```xml
<dependency>
  <groupId>org.dynamicloud.api</groupId>
  <artifactId>lonline</artifactId>
  <version>0.0.1</version>
</dependency>
```

#Settings

Lonline needs a basic settings to be configured, the settings of Lonline are within log4j settings file.

**These are the settings of Lonline:**

```properties
############################################################################################################
######## ###########            ##   ############ ## ############ ##   ############# ##         ############
######## ########### ########## ## ##  ########## ## ############ ## ###  ########## ## ####################
######## ########### ########## ## ####  ######## ## ############ ## #####  ######## ## ####################
######## ########### ########## ## ######  ###### ## ############ ## #######  ###### ##      ###############
######## ########### ########## ## ########  #### ## ############ ## #########  #### ## ####################
######## ########### ########## ## ##########  ## ## ############ ## ###########  ## ## ####################
########           #            ## ############   ##           ## ## #############   ##         ############
############################################################################################################
log4j.org.dynamicloud.lonline=LONLINE
# Lonline appender powered by Dynamicloud
log4j.appender.LONLINE=org.dynamicloud.lonline.appender.LonlineAppender
# This is the model identifier for test and development environment
# The model contains the structure to store logs into the cloud
# For more information about models in Dynamicloud visit https://www.dynamicloud.org/documents/apidoc#main_concepts
log4j.appender.LONLINE.modelIdentifier=Enter_Model_Id
# Credentials for REST APIs
# Go to https://www.dynamicloud.org/manage and get the API keys available in your profile
# If you don't have an account in Dynamicloud, visit https://www.dynamicloud.org/signupform
# You can easily use a social network to sign up
log4j.appender.LONLINE.Csk=Enter_Client_Secret_Key
log4j.appender.LONLINE.Aci=Enter_API_Client_Id
# Shows every warning like rejected request from Dynamicloud and other warnings in lonline
log4j.appender.LONLINE.Warning=true
# report_limit indicates how many records lonline will execute to fetch data from Dynamicloud.
# If you need to increase this value, please think about the available requests per month in your account.
# Dynamicloud uses a limit of records per request, at this time the max records per request is 20.  So,
# report_limit=100 are 5 request.
log4j.appender.LONLINE.ReportLimit=100
# Send the backtrace (the ordered method calls) of the log.  If you want to avoid this sets to false
log4j.appender.LONLINE.Backtrace=true
# async = true is the best choice to avoid impact in your app's execution.
# If you want to wait for every request, set async: false
log4j.appender.LONLINE.Async=true
############################################################################################################
############################################################################################################
# End of Lonline appender ##################################################################################
############################################################################################################
############################################################################################################
```

###Dynamicloud account

Lonline needs API credentials from a Dynamicloud account, these credentials allow Lonline to access your account's structure (Model).  The mandatory model in your account should be composed for a model with at least three fields.  For further information about models and fields in Dynamicloud visit its documentation at [Main concepts](https://www.dynamicloud.org/documents/apidoc#main_concepts "Dynamicloud documentation")

**We are going to explain step by step how to setup your account in Dynamicloud, trust us it's very easy:**

1. Sign up in Dynamicloud (You can use either Google, Linkedin or Github account to speed up the registration)
2. Click on **Add Field** link at your **Real time Dashboard**.  Here you need to add three fields:
  
a. **Fields:**
  
| Field identifier | Field label | Field comments | Field type | Is a required field in form? |
| --- | --- | --- | --- | --- |
| `lonlinetext` | Log text | Contains the trace of this log | Textarea | **Yes** |
| `lonlinelevel` | Log level | Contains the log level | Combobox | **Yes** |
| `lonlinetrace` | Complete Trace | Contains the complete trace of the log | Textarea | **No** |  
  
b. **`lonlinelevel` is a combobox.  You need to add the following options:**
  
| Value | Text |
| --- | --- |
| `Fatal` | Fatal |
| `error` | Error |
| `warn` | Warn |
| `Info` | Info |
| `debug` | Debug |
| `trace` | Trace |

**To add these options follow the steps below:**

1. Click on **Global Fields** link at your **Real time Dashboard**
2. In the table of fields find the row with the identifier `lonlinelevel`
3. Click on **Manage items** link.  An empty list of items will be shown, there click on **Add new** button and fill the value and text field
4. The step number three should be executed six times according to the available levels (Fatal, Error, Warn, Info, Debug and Trace).

c. **Add model**
  
A model is the cointainer of these fields, to add a model follow the next steps:

1. Click on **Add model** link at your **Real time Dashboard**
2. Fill the mandatory field name and set a description (Optional)
3. Press button Create
4. In the list of Models the Model box has a header with the model Id, copy that Id and put it in your `log4j.properties` file.

```properties
# This is the model identifier for test and development environment
# The model contains the structure to store logs into the cloud
# For more information about models in Dynamicloud visit https://www.dynamicloud.org/documents/mfdoc
log4j.appender.LONLINE.modelIdentifier=Enter_Model_Id
```

####The last step is to copy the API credentials (CSK and ACI keys) to put them in your `log4j.properties` file.

1. Click on **Your name link at left top of your account**
2. Copy the CSK and ACI keys and put them into your `log4j.properties` file.

```properties
# Credentials for REST APIs
# Go to https://www.dynamicloud.org/manage and get the API keys available in your profile
# If you don't have an account in Dynamicloud, visit https://www.dynamicloud.org/signupform
# You can easily use a social network to sign up
log4j.appender.LONLINE.Csk=Enter_Client_Secret_Key
log4j.appender.LONLINE.Aci=Enter_API_Client_Id
```

At this moment you have the necessary to start to log your program into the cloud.

#How to use
Lonline is easy to use, one line of code logs and stores into the cloud.

###Log using the six levels

```java
Logger logger = Logger.getLogger('org.package');

logger.trace('Calling method Y');
logger.debug('Creating new object');
logger.info('Process has finished');
logger.warn("It could'n create this object, the process will try later.");
logger.error('Unable to load setting file');
logger.fatal('The app has crashed and its state is unavailable');
```

###Additional data
Lonline allows you to log further data.  If you want to log more information (For example: The module in your application who has executed the log.) just pass a LonlineLog object with the attributes and values.  Remember that these additional attributes must match with the fields in your model, so you need to add these fields before to log.

**To log additional information, follow the code below:**
```java
Logger logger = Logger.getLogger('org.package');

// additionalInformation is an object that extends from LonlineLog class.  That class must add the additional attributes and bind 
// every set method with the fields in your model.
// For further information, read the lonline java documentation.
logger.trace('Calling method Y', additionalInformation);
```

###Execute reports
Lonline allows you to execute reports about the executed logs and count how many logs have been created so far.

```java
Calendar instance = Calendar.getInstance();
instance.add(Calendar.DATE, -7);

Date to = new Date();
Date from = instance.getTime();

LonlineReport.fetch(LonlineLevel.ERROR, from, to, LonlineLog.class, new LonlineReportCallback() {
    /**
     * This method will be called for each retrieved log from Dynamicloud
     * @param log retrieved log from Dynamicloud
     */
    @Override
    public void manageLog(LonlineLog log) {
        System.out.println("log = " + log.getText());
    }
});

Long count = LonlineReport.count(LonlineLevel.ERROR, from, to);
System.out.println("Count = " + count);
```

For further information and support about lonline, contact us at [Contact](https://www.dynamicloud.org/contact "Dynamicloud contact")
