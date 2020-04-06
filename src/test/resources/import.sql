--
-- JBoss, Home of Professional Open Source
-- Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- This script initializes the database by creating tables and inserting data
-- Use only for a proof of concept

-- You can use this file to load seed data into the database using SQL statements
insert into Member (name, email, phonenumber,password,CreatedDate) values (  'John Smith', 'john.smith@mailinator.com', '2125551212','john316','2020-03-01 12:00:00.70');

INSERT INTO TOPIC(TOPICID,TopicDescription,TopicName) VALUES (1, 'Topic about drumvideos and drummers', 'Drumvideos');
INSERT INTO TOPIC(TOPICID,TopicDescription,TopicName) VALUES (2,'Topic about gigs for drummers', 'Gigs');
INSERT INTO TOPIC(TOPICID,TopicDescription,TopicName) VALUES (3, 'Bands looking for drummers', 'Bands');
INSERT INTO TOPIC(TOPICID,TopicDescription,TopicName) VALUES (4,'Concerts with drummers', 'Concerts');
INSERT INTO TOPIC(TOPICID,TopicDescription,TopicName) VALUES (5, 'Drummers sharing drumlessons', 'Drum lessons');

INSERT INTO memberblogpost(blogPostId,BlogPostBody,BlogPostTitle,MemberAuthorId,TopicId,DatePosted) VALUES(1,'Body','Title',1,2,'2020-04-05 12:00:00.70');

