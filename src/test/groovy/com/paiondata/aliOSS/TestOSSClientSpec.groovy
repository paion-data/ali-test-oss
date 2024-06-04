package com.paiondata.aliOSS

import spock.lang.Specification

class TestOSSClientSpec extends Specification{
    static final String BUCKET_NAME = "test-bucket"
    static final String FILE_ID = "19uyu29"

    def "TestOSSClient upload file and down file"() {
        given: "mock fileContent to upload and testClient"
        final InputStream expectInputStream = new ByteArrayInputStream("test".getBytes())
        TestOSSClient testOSSClient = new TestOSSClient()

        when: "upload file by using TestOSSClient"
        testOSSClient.putObject(BUCKET_NAME, FILE_ID, expectInputStream)

        then: "download file by using TestOSSClient and test the file content"
        InputStream actualInputStream = testOSSClient.getObject(BUCKET_NAME, FILE_ID).getObjectContent()
        "test" == actualInputStream.getText()

        cleanup:
        expectInputStream.close()
        actualInputStream.close()
    }

    def "Download the file repeatedly"() {
        given: "mock fileContent to upload and testClient"
        final InputStream expectInputStream = new ByteArrayInputStream("test".getBytes())
        TestOSSClient testOSSClient = new TestOSSClient()

        when: "upload file by using TestOSSClient"
        testOSSClient.putObject(BUCKET_NAME, FILE_ID, expectInputStream)

        then: "download the file repeatedly and compare the file content"
        InputStream firstInputStream = testOSSClient.getObject(BUCKET_NAME, FILE_ID).getObjectContent()
        InputStream secondInputStream = testOSSClient.getObject(BUCKET_NAME, FILE_ID).getObjectContent()
        firstInputStream.getText() == secondInputStream.getText()

        cleanup:
        expectInputStream.close()
        firstInputStream.close()
        secondInputStream.close()
    }
}
