#Using Eclipse for the first time

# Introduction #

If you haven't already, please go back and check out my [Getting Started](https://code.google.com/p/androidbirdseye/wiki/GettingStarted) Tutorial before beginning this one.

This tutorial will cover the basics of creating a project in Eclipse. More specifically, a project using this engine.

## **Step One** ##

Once Eclipse is opened you will need to create a new project. Go to _**File**_ --> _**New**_ --> _**Android Application Project**_

This will prompt a pop-up screen. For _**Application Name**_ you may choose to name it whatever you like. The important naming key is the **Package Name** which should be named **com.twod.feralvillage** this is to make it easier to implement the java files we have already created. We will also be targeting the _SDK_ **API 9 (Gingerbread)**.

Click _**Next**_ and uncheck the box to create a custom icon. We can do those later. When prompted to create an Activity just chose the blank one. Click _**Next**_ and leave all the fields as they are. We will not be using this class later on. Now click _**Finish**_ and you should have a new project under the name you chose as your _**Application Name**_.

Once you create your project it will automatically open up a screen for your new activity and layout, we can just close these out for now. Now we have our new project ready to import the engine files to!

Let's begin by importing the engine files into the src folder.

## **Step Two** ##

_**Right Click**_ on the package location _**com.twod.feralvillage**_ on the left side of the screen and chose the option to _**Import...**_. This will proceed to open another pop-up window, chose _**File System**_ and continue with _**Next**_. You will now need to navigate to where you have saved the unzipped .java files from **feralvillage.zip**. Once you have found the directory, select all the .java files from this engine and select _**Finish**_.

Don't be concerned when you start to see errors showing up after this step, this is because we still need to import the res files that these .java files are making reference to.

## **Step Three** ##

Navigate to where your **res** folder is on the left menu screen. Click it to open up the dropdown. You will likely need to add a few folders such as _anim, drawable, raw, xml, values-dpad, values-nonav, values-wheel, and drawable-normal-mdpi_. To add these folders simply _**Right Click**_ the _**res folder**_ and select _**New**_ --> _**Folder**_.

Now we need to use the same import method to get the files from **res.zip**. This might take a while to get through all the different folders.

Once you have imported all the res files into your project there is only one more file that needs to be edited.

## **Step Four** ##

Using the Left menu screen, navigate to _**AndroidManifest.xml**_ and double click the file to open it up. Using the code from [This Engine Manifest](https://code.google.com/p/androidbirdseye/source/browse/trunk/AndroidManifest.xml) replace the code in your current AndroidManifest.xml file by copying and pasting.

Your project should now be free from errors and fully functional with the current **Android Bird's Eye** engine files.

To test out your project and make sure everything is working _**Right Click**_ on your project name and Select _**Run As**_ --> _**Android Application**_.

You may need to configure your _**Run Configurations**_ so that you can test your project on your Android Phone rather then the AVD (usually super slow and buggy). To use your phone simply plug it in and select _**System Settings**_ --> _**Developer Options**_ --> _**USB Debugging**_ on the phone itself.

In the next tutorial we will cover changing drawables and other resources.