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
        expectInputStream == actualInputStream

        cleanup:
        expectInputStream.close()
    }
}
