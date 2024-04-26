package com.paiondata.aliOSS

import spock.lang.Specification


class TestOSSClientSpec extends Specification{
    static final String BUCKET_NAME = "test-bucket"
    static final String FILE_ID = "19uyu29"

    def "test TestOSSClient upload file and down file"() {

        given:
        File file = Mock(File.class)
        InputStream expectInputStream = new FileInputStream(file)
        TestOSSClient testOSSClient = new TestOSSClient()

        then:
        testOSSClient.putObject(BUCKET_NAME, FILE_ID, expectInputStream)

        when:
        print()
    }
}
