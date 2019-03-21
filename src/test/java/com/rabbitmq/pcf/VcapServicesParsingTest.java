/*
 * Copyright (c) 2018 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rabbitmq.pcf;

import org.junit.jupiter.api.Test;

import static com.rabbitmq.pcf.PcfPerfTest.uris;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 */
public class VcapServicesParsingTest {

    final static String SINGLE_RABBITMQ_SERVICE = " {\"p-rabbitmq\":[{\n"
        + "      \"credentials\": {\n"
        + "        \"http_api_uris\": [\n"
        + "          \"https://fe6e112b-0f85-428d-8a9d-16a939f3bc92:sk6rmkdkh9vblco5d4diec7dku@rabbitmq-management.local.pcfdev.io/api/\"\n"
        + "        ],\n"
        + "        \"ssl\": false,\n"
        + "        \"dashboard_url\": \"https://rabbitmq-management.local.pcfdev.io/#/login/fe6e112b-0f85-428d-8a9d-16a939f3bc92/sk6rmkdkh9vblco5d4diec7dku\",\n"
        + "        \"password\": \"sk6rmkdkh9vblco5d4diec7dku\",\n"
        + "        \"protocols\": {\n"
        + "          \"amqp\": {\n"
        + "            \"vhost\": \"e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "            \"username\": \"fe6e112b-0f85-428d-8a9d-16a939f3bc92\",\n"
        + "            \"password\": \"sk6rmkdkh9vblco5d4diec7dku\",\n"
        + "            \"port\": 5672,\n"
        + "            \"host\": \"rabbitmq.local.pcfdev.io\",\n"
        + "            \"hosts\": [\n"
        + "              \"rabbitmq.local.pcfdev.io\"\n"
        + "            ],\n"
        + "            \"ssl\": false,\n"
        + "            \"uri\": \"amqp://fe6e112b-0f85-428d-8a9d-16a939f3bc92:sk6rmkdkh9vblco5d4diec7dku@rabbitmq.local.pcfdev.io:5672/e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "            \"uris\": [\n"
        + "              \"amqp://fe6e112b-0f85-428d-8a9d-16a939f3bc92:sk6rmkdkh9vblco5d4diec7dku@rabbitmq.local.pcfdev.io:5672/e92537fe-112f-4958-8d6a-8dc00b1364d3\"\n"
        + "            ]\n"
        + "          },\n"
        + "          \"management\": {\n"
        + "            \"path\": \"/api/\",\n"
        + "            \"ssl\": false,\n"
        + "            \"hosts\": [\n"
        + "              \"rabbitmq.local.pcfdev.io\"\n"
        + "            ],\n"
        + "            \"password\": \"sk6rmkdkh9vblco5d4diec7dku\",\n"
        + "            \"username\": \"fe6e112b-0f85-428d-8a9d-16a939f3bc92\",\n"
        + "            \"port\": 15672,\n"
        + "            \"host\": \"rabbitmq.local.pcfdev.io\",\n"
        + "            \"uri\": \"http://fe6e112b-0f85-428d-8a9d-16a939f3bc92:sk6rmkdkh9vblco5d4diec7dku@rabbitmq.local.pcfdev.io:15672/api/\",\n"
        + "            \"uris\": [\n"
        + "              \"http://fe6e112b-0f85-428d-8a9d-16a939f3bc92:sk6rmkdkh9vblco5d4diec7dku@rabbitmq.local.pcfdev.io:15672/api/\"\n"
        + "            ]\n"
        + "          }\n"
        + "        },\n"
        + "        \"username\": \"fe6e112b-0f85-428d-8a9d-16a939f3bc92\",\n"
        + "        \"hostname\": \"rabbitmq.local.pcfdev.io\",\n"
        + "        \"hostnames\": [\n"
        + "          \"rabbitmq.local.pcfdev.io\"\n"
        + "        ],\n"
        + "        \"vhost\": \"e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "        \"http_api_uri\": \"https://fe6e112b-0f85-428d-8a9d-16a939f3bc92:sk6rmkdkh9vblco5d4diec7dku@rabbitmq-management.local.pcfdev.io/api/\",\n"
        + "        \"uri\": \"amqp://fe6e112b-0f85-428d-8a9d-16a939f3bc92:sk6rmkdkh9vblco5d4diec7dku@rabbitmq.local.pcfdev.io/e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "        \"uris\": [\n"
        + "          \"amqp://fe6e112b-0f85-428d-8a9d-16a939f3bc92:sk6rmkdkh9vblco5d4diec7dku@rabbitmq.local.pcfdev.io/e92537fe-112f-4958-8d6a-8dc00b1364d3\"\n"
        + "        ]\n"
        + "      },\n"
        + "      \"syslog_drain_url\": null,\n"
        + "      \"volume_mounts\": [\n"
        + "      ],\n"
        + "      \"label\": \"p-rabbitmq\",\n"
        + "      \"provider\": null,\n"
        + "      \"plan\": \"standard\",\n"
        + "      \"name\": \"my-rabbitmq\",\n"
        + "      \"tags\": [\n"
        + "        \"rabbitmq\",\n"
        + "        \"messaging\",\n"
        + "        \"message-queue\",\n"
        + "        \"amqp\",\n"
        + "        \"stomp\",\n"
        + "     \"mqtt\",\n"
        + "     \"pivotal\"\n"
        + "   ]\n"
        + " }]}\n";

    final static String TWO_RABBITMQ_SERVICES = "{\"p-rabbitmq\":[{\n"
        + "   \"credentials\": {\n"
        + "     \"http_api_uris\": [\n"
        + "       \"https://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq-management.local.pcfdev.io/api/\"\n"
        + "     ],\n"
        + "     \"ssl\": false,\n"
        + "     \"dashboard_url\": \"https://rabbitmq-management.local.pcfdev.io/#/login/238356f9-5436-4681-9275-9874bf503f83/940ph0mouteu06dtmf5esprte9\",\n"
        + "     \"password\": \"940ph0mouteu06dtmf5esprte9\",\n"
        + "     \"protocols\": {\n"
        + "       \"amqp\": {\n"
        + "         \"vhost\": \"e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "         \"username\": \"238356f9-5436-4681-9275-9874bf503f83\",\n"
        + "         \"password\": \"940ph0mouteu06dtmf5esprte9\",\n"
        + "         \"port\": 5672,\n"
        + "         \"host\": \"rabbitmq.local.pcfdev.io\",\n"
        + "         \"hosts\": [\n"
        + "           \"rabbitmq.local.pcfdev.io\"\n"
        + "         ],\n"
        + "         \"ssl\": false,\n"
        + "         \"uri\": \"amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io:5672/e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "         \"uris\": [\n"
        + "           \"amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io:5672/e92537fe-112f-4958-8d6a-8dc00b1364d3\"\n"
        + "         ]\n"
        + "       },\n"
        + "       \"management\": {\n"
        + "         \"path\": \"/api/\",\n"
        + "         \"ssl\": false,\n"
        + "         \"hosts\": [\n"
        + "           \"rabbitmq.local.pcfdev.io\"\n"
        + "         ],\n"
        + "         \"password\": \"940ph0mouteu06dtmf5esprte9\",\n"
        + "         \"username\": \"238356f9-5436-4681-9275-9874bf503f83\",\n"
        + "         \"port\": 15672,\n"
        + "         \"host\": \"rabbitmq.local.pcfdev.io\",\n"
        + "         \"uri\": \"http://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io:15672/api/\",\n"
        + "         \"uris\": [\n"
        + "           \"http://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io:15672/api/\"\n"
        + "         ]\n"
        + "       }\n"
        + "     },\n"
        + "     \"username\": \"238356f9-5436-4681-9275-9874bf503f83\",\n"
        + "     \"hostname\": \"rabbitmq.local.pcfdev.io\",\n"
        + "     \"hostnames\": [\n"
        + "       \"rabbitmq.local.pcfdev.io\"\n"
        + "     ],\n"
        + "     \"vhost\": \"e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "     \"http_api_uri\": \"https://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq-management.local.pcfdev.io/api/\",\n"
        + "     \"uri\": \"amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io/e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "     \"uris\": [\n"
        + "       \"amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io/e92537fe-112f-4958-8d6a-8dc00b1364d3\"\n"
        + "     ]\n"
        + "   },\n"
        + "   \"syslog_drain_url\": null,\n"
        + "   \"volume_mounts\": [\n"
        + "   ],\n"
        + "   \"label\": \"p-rabbitmq\",\n"
        + "   \"provider\": null,\n"
        + "   \"plan\": \"standard\",\n"
        + "   \"name\": \"my-rabbitmq\",\n"
        + "   \"tags\": [\n"
        + "     \"rabbitmq\",\n"
        + "     \"messaging\",\n"
        + "     \"message-queue\",\n"
        + "     \"amqp\",\n"
        + "     \"stomp\",\n"
        + "     \"mqtt\",\n"
        + "     \"pivotal\"\n"
        + "   ]\n"
        + " },{\n"
        + "   \"credentials\": {\n"
        + "     \"http_api_uris\": [\n"
        + "       \"https://4e12b9d2-3416-41c7-9d08-f8134f3afdbb:2g65rmrjppibbm8v93g8o7t47d@rabbitmq-management.local.pcfdev.io/api/\"\n"
        + "     ],\n"
        + "     \"ssl\": false,\n"
        + "     \"dashboard_url\": \"https://rabbitmq-management.local.pcfdev.io/#/login/4e12b9d2-3416-41c7-9d08-f8134f3afdbb/2g65rmrjppibbm8v93g8o7t47d\",\n"
        + "     \"password\": \"2g65rmrjppibbm8v93g8o7t47d\",\n"
        + "     \"protocols\": {\n"
        + "       \"amqp\": {\n"
        + "         \"vhost\": \"ff77fec6-1e15-4047-bdb4-0a9cebc31856\",\n"
        + "         \"username\": \"4e12b9d2-3416-41c7-9d08-f8134f3afdbb\",\n"
        + "         \"password\": \"2g65rmrjppibbm8v93g8o7t47d\",\n"
        + "         \"port\": 5672,\n"
        + "         \"host\": \"rabbitmq.local.pcfdev.io\",\n"
        + "         \"hosts\": [\n"
        + "           \"rabbitmq.local.pcfdev.io\"\n"
        + "         ],\n"
        + "         \"ssl\": false,\n"
        + "         \"uri\": \"amqp://4e12b9d2-3416-41c7-9d08-f8134f3afdbb:2g65rmrjppibbm8v93g8o7t47d@rabbitmq.local.pcfdev.io:5672/ff77fec6-1e15-4047-bdb4-0a9cebc31856\",\n"
        + "         \"uris\": [\n"
        + "           \"amqp://4e12b9d2-3416-41c7-9d08-f8134f3afdbb:2g65rmrjppibbm8v93g8o7t47d@rabbitmq.local.pcfdev.io:5672/ff77fec6-1e15-4047-bdb4-0a9cebc31856\"\n"
        + "         ]\n"
        + "       },\n"
        + "       \"management\": {\n"
        + "         \"path\": \"/api/\",\n"
        + "         \"ssl\": false,\n"
        + "         \"hosts\": [\n"
        + "           \"rabbitmq.local.pcfdev.io\"\n"
        + "         ],\n"
        + "         \"password\": \"2g65rmrjppibbm8v93g8o7t47d\",\n"
        + "         \"username\": \"4e12b9d2-3416-41c7-9d08-f8134f3afdbb\",\n"
        + "         \"port\": 15672,\n"
        + "         \"host\": \"rabbitmq.local.pcfdev.io\",\n"
        + "         \"uri\": \"http://4e12b9d2-3416-41c7-9d08-f8134f3afdbb:2g65rmrjppibbm8v93g8o7t47d@rabbitmq.local.pcfdev.io:15672/api/\",\n"
        + "         \"uris\": [\n"
        + "           \"http://4e12b9d2-3416-41c7-9d08-f8134f3afdbb:2g65rmrjppibbm8v93g8o7t47d@rabbitmq.local.pcfdev.io:15672/api/\"\n"
        + "         ]\n"
        + "       }\n"
        + "     },\n"
        + "     \"username\": \"4e12b9d2-3416-41c7-9d08-f8134f3afdbb\",\n"
        + "     \"hostname\": \"rabbitmq.local.pcfdev.io\",\n"
        + "     \"hostnames\": [\n"
        + "       \"rabbitmq.local.pcfdev.io\"\n"
        + "     ],\n"
        + "     \"vhost\": \"ff77fec6-1e15-4047-bdb4-0a9cebc31856\",\n"
        + "     \"http_api_uri\": \"https://4e12b9d2-3416-41c7-9d08-f8134f3afdbb:2g65rmrjppibbm8v93g8o7t47d@rabbitmq-management.local.pcfdev.io/api/\",\n"
        + "     \"uri\": \"amqp://4e12b9d2-3416-41c7-9d08-f8134f3afdbb:2g65rmrjppibbm8v93g8o7t47d@rabbitmq.local.pcfdev.io/ff77fec6-1e15-4047-bdb4-0a9cebc31856\",\n"
        + "     \"uris\": [\n"
        + "       \"amqp://4e12b9d2-3416-41c7-9d08-f8134f3afdbb:2g65rmrjppibbm8v93g8o7t47d@rabbitmq.local.pcfdev.io/ff77fec6-1e15-4047-bdb4-0a9cebc31856\"\n"
        + "     ]\n"
        + "   },\n"
        + "   \"syslog_drain_url\": null,\n"
        + "   \"volume_mounts\": [\n"
        + "   ],\n"
        + "   \"label\": \"p-rabbitmq\",\n"
        + "   \"provider\": null,\n"
        + "   \"plan\": \"standard\",\n"
        + "   \"name\": \"my-rabbitmq-2\",\n"
        + "   \"tags\": [\n"
        + "     \"rabbitmq\",\n"
        + "     \"messaging\",\n"
        + "     \"message-queue\",\n"
        + "     \"amqp\",\n"
        + "     \"stomp\",\n"
        + "     \"mqtt\",\n"
        + "     \"pivotal\"\n"
        + "   ]\n"
        + " }]}";

    static final String NOT_ONLY_RABBITMQ_SERVICES = "{\"p-mysql\":[{\n"
        + "   \"credentials\": {\n"
        + "     \"hostname\": \"mysql-broker.local.pcfdev.io\",\n"
        + "     \"port\": 3306,\n"
        + "     \"name\": \"cf_25c9ea63_47a1_4ce4_8320_2e5db519b12a\",\n"
        + "     \"username\": \"AQIVKoorjxmKaVwQ\",\n"
        + "     \"password\": \"O5MSiy94zQVEkviS\",\n"
        + "     \"uri\": \"mysql://AQIVKoorjxmKaVwQ:O5MSiy94zQVEkviS@mysql-broker.local.pcfdev.io:3306/cf_25c9ea63_47a1_4ce4_8320_2e5db519b12a?reconnect=true\",\n"
        + "     \"jdbcUrl\": \"jdbc:mysql://mysql-broker.local.pcfdev.io:3306/cf_25c9ea63_47a1_4ce4_8320_2e5db519b12a?user=AQIVKoorjxmKaVwQ&password=O5MSiy94zQVEkviS\"\n"
        + "   },\n"
        + "   \"syslog_drain_url\": null,\n"
        + "   \"volume_mounts\": [\n"
        + "   ],\n"
        + "   \"label\": \"p-mysql\",\n"
        + "   \"provider\": null,\n"
        + "   \"plan\": \"512mb\",\n"
        + "   \"name\": \"my-mysql\",\n"
        + "   \"tags\": [\n"
        + "     \"mysql\"\n"
        + "   ]\n"
        + " }],\"p-rabbitmq\":[{\n"
        + "   \"credentials\": {\n"
        + "     \"http_api_uris\": [\n"
        + "       \"https://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq-management.local.pcfdev.io/api/\"\n"
        + "     ],\n"
        + "     \"ssl\": false,\n"
        + "     \"dashboard_url\": \"https://rabbitmq-management.local.pcfdev.io/#/login/238356f9-5436-4681-9275-9874bf503f83/940ph0mouteu06dtmf5esprte9\",\n"
        + "     \"password\": \"940ph0mouteu06dtmf5esprte9\",\n"
        + "     \"protocols\": {\n"
        + "       \"amqp\": {\n"
        + "         \"vhost\": \"e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "         \"username\": \"238356f9-5436-4681-9275-9874bf503f83\",\n"
        + "         \"password\": \"940ph0mouteu06dtmf5esprte9\",\n"
        + "         \"port\": 5672,\n"
        + "         \"host\": \"rabbitmq.local.pcfdev.io\",\n"
        + "         \"hosts\": [\n"
        + "           \"rabbitmq.local.pcfdev.io\"\n"
        + "         ],\n"
        + "         \"ssl\": false,\n"
        + "         \"uri\": \"amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io:5672/e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "         \"uris\": [\n"
        + "           \"amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io:5672/e92537fe-112f-4958-8d6a-8dc00b1364d3\"\n"
        + "         ]\n"
        + "       },\n"
        + "       \"management\": {\n"
        + "         \"path\": \"/api/\",\n"
        + "         \"ssl\": false,\n"
        + "         \"hosts\": [\n"
        + "           \"rabbitmq.local.pcfdev.io\"\n"
        + "         ],\n"
        + "         \"password\": \"940ph0mouteu06dtmf5esprte9\",\n"
        + "         \"username\": \"238356f9-5436-4681-9275-9874bf503f83\",\n"
        + "         \"port\": 15672,\n"
        + "         \"host\": \"rabbitmq.local.pcfdev.io\",\n"
        + "         \"uri\": \"http://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io:15672/api/\",\n"
        + "         \"uris\": [\n"
        + "           \"http://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io:15672/api/\"\n"
        + "         ]\n"
        + "       }\n"
        + "     },\n"
        + "     \"username\": \"238356f9-5436-4681-9275-9874bf503f83\",\n"
        + "     \"hostname\": \"rabbitmq.local.pcfdev.io\",\n"
        + "     \"hostnames\": [\n"
        + "       \"rabbitmq.local.pcfdev.io\"\n"
        + "     ],\n"
        + "     \"vhost\": \"e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "     \"http_api_uri\": \"https://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq-management.local.pcfdev.io/api/\",\n"
        + "     \"uri\": \"amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io/e92537fe-112f-4958-8d6a-8dc00b1364d3\",\n"
        + "     \"uris\": [\n"
        + "       \"amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io/e92537fe-112f-4958-8d6a-8dc00b1364d3\"\n"
        + "     ]\n"
        + "   },\n"
        + "   \"syslog_drain_url\": null,\n"
        + "   \"volume_mounts\": [\n"
        + "   ],\n"
        + "   \"label\": \"p-rabbitmq\",\n"
        + "   \"provider\": null,\n"
        + "   \"plan\": \"standard\",\n"
        + "   \"name\": \"my-rabbitmq\",\n"
        + "   \"tags\": [\n"
        + "     \"rabbitmq\",\n"
        + "     \"messaging\",\n"
        + "     \"message-queue\",\n"
        + "     \"amqp\",\n"
        + "     \"stomp\",\n"
        + "     \"mqtt\",\n"
        + "     \"pivotal\"\n"
        + "   ]\n"
        + " }]}";

    static final String NO_RABBITMQ_SERVICE = "{\"p-mysql\":[{\n"
        + "   \"credentials\": {\n"
        + "     \"hostname\": \"mysql-broker.local.pcfdev.io\",\n"
        + "     \"port\": 3306,\n"
        + "     \"name\": \"cf_25c9ea63_47a1_4ce4_8320_2e5db519b12a\",\n"
        + "     \"username\": \"AQIVKoorjxmKaVwQ\",\n"
        + "     \"password\": \"O5MSiy94zQVEkviS\",\n"
        + "     \"uri\": \"mysql://AQIVKoorjxmKaVwQ:O5MSiy94zQVEkviS@mysql-broker.local.pcfdev.io:3306/cf_25c9ea63_47a1_4ce4_8320_2e5db519b12a?reconnect=true\",\n"
        + "     \"jdbcUrl\": \"jdbc:mysql://mysql-broker.local.pcfdev.io:3306/cf_25c9ea63_47a1_4ce4_8320_2e5db519b12a?user=AQIVKoorjxmKaVwQ&password=O5MSiy94zQVEkviS\"\n"
        + "   },\n"
        + "   \"syslog_drain_url\": null,\n"
        + "   \"volume_mounts\": [\n"
        + "   ],\n"
        + "   \"label\": \"p-mysql\",\n"
        + "   \"provider\": null,\n"
        + "   \"plan\": \"512mb\",\n"
        + "   \"name\": \"my-mysql\",\n"
        + "   \"tags\": [\n"
        + "     \"mysql\"\n"
        + "   ]\n"
        + " }]}";

    static final String USER_PROVIDED_SERVICE = "{\n"
        + "   \"user-provided\":[\n"
        + "      {\n"
        + "         \"binding_name\":null,\n"
        + "         \"credentials\":{\n"
        + "            \"urls\":[\n"
        + "               \"amqp://admin:8j6liQN5t0YdCaYsbpAJ@10.0.32.4:5672/%2F\"\n"
        + "            ]\n"
        + "         },\n"
        + "         \"instance_name\":\"rmq\",\n"
        + "         \"label\":\"user-provided\",\n"
        + "         \"name\":\"rmq\",\n"
        + "         \"syslog_drain_url\":\"\",\n"
        + "         \"tags\":[\n"
        + "\n"
        + "         ],\n"
        + "         \"volume_mounts\":[\n"
        + "\n"
        + "         ]\n"
        + "      }\n"
        + "   ]\n"
        + "}";

    @Test
    public void extractUriSingleService() {
        assertEquals(
            "amqp://fe6e112b-0f85-428d-8a9d-16a939f3bc92:sk6rmkdkh9vblco5d4diec7dku@rabbitmq.local.pcfdev.io/e92537fe-112f-4958-8d6a-8dc00b1364d3",
            uris(SINGLE_RABBITMQ_SERVICE)
        );
    }

    @Test
    public void extractUriSeveralRabbitMqServicesShouldTakeFirst() {
        assertEquals(
            "amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io/e92537fe-112f-4958-8d6a-8dc00b1364d3",
            uris(TWO_RABBITMQ_SERVICES)
        );
    }

    @Test
    public void extractUriNotOnlyRabbitMqServicesShouldPickRabbitMqUri() {
        assertEquals(
            "amqp://238356f9-5436-4681-9275-9874bf503f83:940ph0mouteu06dtmf5esprte9@rabbitmq.local.pcfdev.io/e92537fe-112f-4958-8d6a-8dc00b1364d3",
            uris(NOT_ONLY_RABBITMQ_SERVICES)
        );
    }

    @Test
    public void extractUriFromUserProvidedServiceNoAmqpTag() {
        assertEquals(
            "amqp://admin:8j6liQN5t0YdCaYsbpAJ@10.0.32.4:5672/%2F",
            uris(USER_PROVIDED_SERVICE)
        );
    }

    @Test
    public void extractUriNoRabbitMqServiceShouldReturnNull() {
        assertNull(uris(NO_RABBITMQ_SERVICE));
    }
}
