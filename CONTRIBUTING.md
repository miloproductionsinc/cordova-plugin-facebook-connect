# Contributing to the Cordova Facebook Connect Plugin

:+1::tada: First off, thanks for taking the time to contribute! :tada::+1:

The following is a set of guidelines for contributing to the PhoneGap Facebook Connect Plugin.

These are just guidelines, not rules, use your best judgement and feel free to propose changes to this document in a pull request.

## Getting Help

Before creating a Github issue make sure you...

- Check the [Troubleshooting Guide](./docs/TROUBLESHOOTING.md) for well known issues.
- Search the [issues list](https://github.com/Wizcorp/phonegap-facebook-plugin/issues) for existing closed or still open issues
- Create a Github issue

#### Creating an Issue

When submitting an issue please add as much of the following information:

- Tell us what you expected to happen and what actually happened. ("It doesn't work" is **not** a solveable problem)
- Provide sample code so people can test and debug the problem.
- Provide `adb logcat` or Xcode console output.

Can you solve the problem yourself? If so see the next section on Pull Requests and send us the fix!!! <3

## Pull Requests

- Include screenshots and animated GIFs in your pull request whenever possible.
- Try to follow the [JavaScript](https://github.com/Wizcorp/javascript-styleguide/blob/master/README.md), [Objective-C](http://google-styleguide.googlecode.com/svn/trunk/objcguide.xml) or [Java](https://google-styleguide.googlecode.com/svn/trunk/javaguide.html) style guides. Although these are just guides, please at least support continuety (that means **using 4 spaces please**).
- **Send your pull request to the develop branch.**
- If you can, provide sample testing code.  (Or an actual test!)

## Running Tests

In your cordova project (or a new project):
- `cordova plugin add cordova-plugin-test-framework`
- `cordova plugin add --link <rel path to cordova-plugin-facebook-connect> --variable APP_ID="<real app id>" --variable APP_NAME="My App"`
  - `--link` creates a link between the objc/java files so that you can modify them directly from your project
  - Windows may require admin permission to use `--link`
  - This does **not** link any js files
- `cordova plugin add <rel path to cordova-plugin-facebook-connect>/tests`
- in *config.xml* set `<content src="cdvtests/index.html" />`
