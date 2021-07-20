# Club Events App

<details open="open">
  <summary>Table of Contents</summary>
  <ul>
    <li>
      <a href="#video-demonstration">Video Demonstration</a>
    </li>
    <li>
      <a href="#about-the-website">About the app</a>
    </li>
    <li>
      <a href="#key-highlights">Features</a>
    </li>
   <li>
      <a href="#getting-started">Getting Started - Techstacks used, installation, execution</a>
    </li>
   <li>
      <a href="#usage">Usage</a>
    </li>
  </ul>
</details>

## [Video Demonstration](https://youtu.be/y1cnkqBNZAM)

## About The App

A project to scrape posts from Facebook webpages using Selenium and Jsoup, store them in Cloud Firestore database, retrieve and display them in an Android app. This has applications for college students where most of the important announcements regarding upcoming events are made through social media posts, giving them a one stop place to access information.

## Features
* Login/Signup
* IITG clubs grouped by category (Technical/Cultural/Sports)
* Facebook posts of each club at one place
* Each post's content, timestamp, facebook link, images attached to posts if any, etc. can be viewed
* Developer and contact us sections

## Getting Started

### Techstacks used

* Selenium
* Jsoup
* Cloud Firestore
* Android Studio
* Java
* XML

### Installation 

  Clone the repo
```
    git clone https://github.com/sg-iitg/Club-Events
```
#### Backend
1.  Open Club-Events-Back-End folder in IDE of your choice

2. Install Apache Maven and add the bin directory to the PATH environment variable

3. Install all dependencies
```
    mvn install
```
4. Compile using
```
    mvn compile
```
#### Frontend
1. Install Android Studio
2. Open Club-Events-Front-End folder in studio
3. All required dependencies are automatically installed by studio

### Execution

#### Backend
 Run the backend script using below command to scrape latest posts and update the database
```
    mvn exec:java -D"exec.mainClass"="myTestPackage.clubs"
```
#### Frontend
 Connect your mobile using USB cable or create an apk to install the app on your device

## Usage

The application consists of 2 different parts:
* Backend script which scrapes posts from Facebook pages of different clubs and stores them in Firestore database
* All posts from the database are then retrieved and displayed in an android application.


You can either login or signup depending on whether you are visiting the application for the first time or are a regular user:
![](/window_snippets/login_page.png)

All clubs grouped by category can be found here:
![](/window_snippets/club_list_page.png)

Each drop down covers all clubs of each category:
![](/window_snippets/clubs_dropdown.png)

For each club, recent Facebook posts along with the content, timestamp of release, image if any in the posts, and the facebook link are displayed in the form of cards:
![](/window_snippets/post.png)

You can visit facebook to view the actual post and like/comment using the link provided:
![](/window_snippets/facebook_page.png)
