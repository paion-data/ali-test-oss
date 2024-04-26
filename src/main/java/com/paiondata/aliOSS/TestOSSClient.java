/*
 * Copyright 2024 Paion Data
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.paiondata.aliOSS;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.Credentials;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.AbortMultipartUploadRequest;
import com.aliyun.oss.model.AccessControlList;
import com.aliyun.oss.model.AddBucketCnameRequest;
import com.aliyun.oss.model.AddBucketCnameResult;
import com.aliyun.oss.model.AddBucketReplicationRequest;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.AsyncFetchTaskConfiguration;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.BucketList;
import com.aliyun.oss.model.BucketLoggingResult;
import com.aliyun.oss.model.BucketMetadata;
import com.aliyun.oss.model.BucketProcess;
import com.aliyun.oss.model.BucketQosInfo;
import com.aliyun.oss.model.BucketReferer;
import com.aliyun.oss.model.BucketReplicationProgress;
import com.aliyun.oss.model.BucketStat;
import com.aliyun.oss.model.BucketVersioningConfiguration;
import com.aliyun.oss.model.BucketWebsiteResult;
import com.aliyun.oss.model.CORSConfiguration;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CnameConfiguration;
import com.aliyun.oss.model.CompleteBucketWormRequest;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.CopyObjectRequest;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.CreateBucketCnameTokenRequest;
import com.aliyun.oss.model.CreateBucketCnameTokenResult;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.CreateBucketVpcipRequest;
import com.aliyun.oss.model.CreateDirectoryRequest;
import com.aliyun.oss.model.CreateLiveChannelRequest;
import com.aliyun.oss.model.CreateLiveChannelResult;
import com.aliyun.oss.model.CreateSelectObjectMetadataRequest;
import com.aliyun.oss.model.CreateSymlinkRequest;
import com.aliyun.oss.model.CreateUdfApplicationRequest;
import com.aliyun.oss.model.CreateUdfRequest;
import com.aliyun.oss.model.CreateVpcipRequest;
import com.aliyun.oss.model.CreateVpcipResult;
import com.aliyun.oss.model.DeleteBucketCnameRequest;
import com.aliyun.oss.model.DeleteBucketInventoryConfigurationRequest;
import com.aliyun.oss.model.DeleteBucketReplicationRequest;
import com.aliyun.oss.model.DeleteBucketVpcipRequest;
import com.aliyun.oss.model.DeleteDirectoryRequest;
import com.aliyun.oss.model.DeleteDirectoryResult;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.DeleteVersionRequest;
import com.aliyun.oss.model.DeleteVersionsRequest;
import com.aliyun.oss.model.DeleteVersionsResult;
import com.aliyun.oss.model.DeleteVpcipRequest;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.DownloadFileResult;
import com.aliyun.oss.model.ExtendBucketWormRequest;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GenerateRtmpUriRequest;
import com.aliyun.oss.model.GenerateVodPlaylistRequest;
import com.aliyun.oss.model.GenericRequest;
import com.aliyun.oss.model.GenericResult;
import com.aliyun.oss.model.GetAsyncFetchTaskRequest;
import com.aliyun.oss.model.GetAsyncFetchTaskResult;
import com.aliyun.oss.model.GetBucketCnameTokenRequest;
import com.aliyun.oss.model.GetBucketCnameTokenResult;
import com.aliyun.oss.model.GetBucketImageResult;
import com.aliyun.oss.model.GetBucketInventoryConfigurationRequest;
import com.aliyun.oss.model.GetBucketInventoryConfigurationResult;
import com.aliyun.oss.model.GetBucketPolicyResult;
import com.aliyun.oss.model.GetBucketReplicationProgressRequest;
import com.aliyun.oss.model.GetBucketRequestPaymentResult;
import com.aliyun.oss.model.GetBucketResourceGroupResult;
import com.aliyun.oss.model.GetBucketWormResult;
import com.aliyun.oss.model.GetImageStyleResult;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.GetUdfApplicationLogRequest;
import com.aliyun.oss.model.GetVodPlaylistRequest;
import com.aliyun.oss.model.HeadObjectRequest;
import com.aliyun.oss.model.InitiateBucketWormRequest;
import com.aliyun.oss.model.InitiateBucketWormResult;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.InventoryConfiguration;
import com.aliyun.oss.model.LifecycleRule;
import com.aliyun.oss.model.ListBucketInventoryConfigurationsRequest;
import com.aliyun.oss.model.ListBucketInventoryConfigurationsResult;
import com.aliyun.oss.model.ListBucketsRequest;
import com.aliyun.oss.model.ListLiveChannelsRequest;
import com.aliyun.oss.model.ListMultipartUploadsRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.ListObjectsV2Request;
import com.aliyun.oss.model.ListObjectsV2Result;
import com.aliyun.oss.model.ListPartsRequest;
import com.aliyun.oss.model.ListVersionsRequest;
import com.aliyun.oss.model.LiveChannel;
import com.aliyun.oss.model.LiveChannelGenericRequest;
import com.aliyun.oss.model.LiveChannelInfo;
import com.aliyun.oss.model.LiveChannelListing;
import com.aliyun.oss.model.LiveChannelStat;
import com.aliyun.oss.model.LiveChannelStatus;
import com.aliyun.oss.model.LiveRecord;
import com.aliyun.oss.model.MultipartUploadListing;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSSymlink;
import com.aliyun.oss.model.ObjectAcl;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.OptionsRequest;
import com.aliyun.oss.model.PartListing;
import com.aliyun.oss.model.Payer;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.ProcessObjectRequest;
import com.aliyun.oss.model.PutBucketImageRequest;
import com.aliyun.oss.model.PutImageStyleRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.RenameObjectRequest;
import com.aliyun.oss.model.ReplicationRule;
import com.aliyun.oss.model.ResizeUdfApplicationRequest;
import com.aliyun.oss.model.RestoreConfiguration;
import com.aliyun.oss.model.RestoreObjectRequest;
import com.aliyun.oss.model.RestoreObjectResult;
import com.aliyun.oss.model.SelectObjectMetadata;
import com.aliyun.oss.model.SelectObjectRequest;
import com.aliyun.oss.model.ServerSideEncryptionConfiguration;
import com.aliyun.oss.model.SetAsyncFetchTaskRequest;
import com.aliyun.oss.model.SetAsyncFetchTaskResult;
import com.aliyun.oss.model.SetBucketAclRequest;
import com.aliyun.oss.model.SetBucketCORSRequest;
import com.aliyun.oss.model.SetBucketEncryptionRequest;
import com.aliyun.oss.model.SetBucketInventoryConfigurationRequest;
import com.aliyun.oss.model.SetBucketLifecycleRequest;
import com.aliyun.oss.model.SetBucketLoggingRequest;
import com.aliyun.oss.model.SetBucketPolicyRequest;
import com.aliyun.oss.model.SetBucketProcessRequest;
import com.aliyun.oss.model.SetBucketQosInfoRequest;
import com.aliyun.oss.model.SetBucketRefererRequest;
import com.aliyun.oss.model.SetBucketRequestPaymentRequest;
import com.aliyun.oss.model.SetBucketResourceGroupRequest;
import com.aliyun.oss.model.SetBucketStorageCapacityRequest;
import com.aliyun.oss.model.SetBucketTaggingRequest;
import com.aliyun.oss.model.SetBucketVersioningRequest;
import com.aliyun.oss.model.SetBucketWebsiteRequest;
import com.aliyun.oss.model.SetLiveChannelRequest;
import com.aliyun.oss.model.SetObjectAclRequest;
import com.aliyun.oss.model.SetObjectTaggingRequest;
import com.aliyun.oss.model.SimplifiedObjectMeta;
import com.aliyun.oss.model.Style;
import com.aliyun.oss.model.TagSet;
import com.aliyun.oss.model.TransferAcceleration;
import com.aliyun.oss.model.UdfApplicationInfo;
import com.aliyun.oss.model.UdfApplicationLog;
import com.aliyun.oss.model.UdfGenericRequest;
import com.aliyun.oss.model.UdfImageInfo;
import com.aliyun.oss.model.UdfInfo;
import com.aliyun.oss.model.UpgradeUdfApplicationRequest;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import com.aliyun.oss.model.UploadPartCopyRequest;
import com.aliyun.oss.model.UploadPartCopyResult;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import com.aliyun.oss.model.UploadUdfImageRequest;
import com.aliyun.oss.model.UserQos;
import com.aliyun.oss.model.UserQosInfo;
import com.aliyun.oss.model.VersionListing;
import com.aliyun.oss.model.VoidResult;
import com.aliyun.oss.model.VpcPolicy;
import com.aliyun.oss.model.Vpcip;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The Ali OSS client used for the test.
 * <p>
 * Use in-memory storage to implement object storage instead of Ali OSS.
 */
public class TestOSSClient implements OSS {

    private final Map<String, InputStream> map = new HashMap<>();

    @Override
    public PutObjectResult putObject(final String bucketName, final String key, final InputStream input)
            throws OSSException, ClientException {
        Objects.requireNonNull(bucketName);
        Objects.requireNonNull(key);
        Objects.requireNonNull(input);

        map.put(key, input);
        return null;
    }

    @Override
    public OSSObject getObject(final String bucketName, final String key) throws OSSException, ClientException {
        Objects.requireNonNull(key);
        Objects.requireNonNull(bucketName);

        final InputStream fileContent = map.get(key);

        final OSSObject ossObject = new OSSObject();
        ossObject.setObjectContent(fileContent);
        ossObject.setKey(key);
        ossObject.setBucketName(bucketName);

        return ossObject;
    }

    @Override
    public void switchCredentials(final Credentials creds) {

    }

    @Override
    public void switchSignatureVersion(final SignVersion signatureVersion) {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public String getConnectionPoolStats() {
        return null;
    }

    @Override
    public Bucket createBucket(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public Bucket createBucket(final CreateBucketRequest createBucketRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucket(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucket(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<Bucket> listBuckets() throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketList listBuckets(final String prefix, final String marker, final Integer maxKeys)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketList listBuckets(final ListBucketsRequest listBucketsRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketAcl(final String bucketName, final CannedAccessControlList acl)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketAcl(final SetBucketAclRequest setBucketAclRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public AccessControlList getBucketAcl(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public AccessControlList getBucketAcl(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketMetadata getBucketMetadata(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketMetadata getBucketMetadata(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketReferer(final String bucketName, final BucketReferer referer)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketReferer(final SetBucketRefererRequest setBucketRefererRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketReferer getBucketReferer(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketReferer getBucketReferer(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public String getBucketLocation(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public String getBucketLocation(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketTagging(final String bucketName, final Map<String, String> tags)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketTagging(final String bucketName, final TagSet tagSet)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketTagging(final SetBucketTaggingRequest setBucketTaggingRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public TagSet getBucketTagging(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public TagSet getBucketTagging(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketTagging(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketTagging(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketVersioningConfiguration getBucketVersioning(final String bucketName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketVersioningConfiguration getBucketVersioning(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketVersioning(final SetBucketVersioningRequest setBucketVersioningRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public boolean doesBucketExist(final String bucketName) throws OSSException, ClientException {
        return false;
    }

    @Override
    public boolean doesBucketExist(final GenericRequest genericRequest) throws OSSException, ClientException {
        return false;
    }

    @Override
    public ObjectListing listObjects(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public ObjectListing listObjects(final String bucketName, final String prefix)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ObjectListing listObjects(final ListObjectsRequest listObjectsRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public ListObjectsV2Result listObjectsV2(final ListObjectsV2Request listObjectsV2Request)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ListObjectsV2Result listObjectsV2(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public ListObjectsV2Result listObjectsV2(final String bucketName, final String prefix)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ListObjectsV2Result listObjectsV2(
            final String bucketName,
            final String prefix,
            final String continuationToken,
            final String startAfter,
            final String delimiter,
            final Integer maxKeys,
            final String encodingType,
            final boolean fetchOwner
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VersionListing listVersions(final String bucketName, final String prefix)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VersionListing listVersions(
            final String bucketName,
            final String prefix,
            final String keyMarker,
            final String versionIdMarker,
            final String delimiter,
            final Integer maxResults
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VersionListing listVersions(final ListVersionsRequest listVersionsRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public PutObjectResult putObject(
            final String bucketName,
            final String key,
            final InputStream input,
            final ObjectMetadata metadata
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public PutObjectResult putObject(
            final String bucketName,
            final String key,
            final File file,
            final ObjectMetadata metadata
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public PutObjectResult putObject(final String bucketName, final String key, final File file)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public PutObjectResult putObject(final PutObjectRequest putObjectRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public PutObjectResult putObject(
            final URL signedUrl,
            final String filePath,
            final Map<String, String> requestHeaders
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public PutObjectResult putObject(
            final URL signedUrl,
            final String filePath,
            final Map<String, String> requestHeaders,
            final boolean useChunkEncoding
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public PutObjectResult putObject(
            final URL signedUrl,
            final InputStream requestContent,
            final long contentLength,
            final Map<String, String> requestHeaders
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public PutObjectResult putObject(
            final URL signedUrl,
            final InputStream requestContent,
            final long contentLength,
            final Map<String, String> requestHeaders,
            final boolean useChunkEncoding
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public CopyObjectResult copyObject(
            final String sourceBucketName,
            final String sourceKey,
            final String destinationBucketName,
            final String destinationKey
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public CopyObjectResult copyObject(final CopyObjectRequest copyObjectRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public ObjectMetadata getObject(final GetObjectRequest getObjectRequest, final File file)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public OSSObject getObject(final GetObjectRequest getObjectRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public OSSObject selectObject(final SelectObjectRequest selectObjectRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public OSSObject getObject(final URL signedUrl, final Map<String, String> requestHeaders)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public SimplifiedObjectMeta getSimplifiedObjectMeta(final String bucketName, final String key)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public SimplifiedObjectMeta getSimplifiedObjectMeta(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ObjectMetadata getObjectMetadata(final String bucketName, final String key)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ObjectMetadata getObjectMetadata(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public SelectObjectMetadata createSelectObjectMetadata(
            final CreateSelectObjectMetadataRequest createSelectObjectMetadataRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ObjectMetadata headObject(final String bucketName, final String key) throws OSSException, ClientException {
        return null;
    }

    @Override
    public ObjectMetadata headObject(final HeadObjectRequest headObjectRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public AppendObjectResult appendObject(final AppendObjectRequest appendObjectRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteObject(final String bucketName, final String key) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteObject(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public DeleteObjectsResult deleteObjects(final DeleteObjectsRequest deleteObjectsRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteVersion(final String bucketName, final String key, final String versionId)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteVersion(final DeleteVersionRequest deleteVersionRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public DeleteVersionsResult deleteVersions(final DeleteVersionsRequest deleteVersionsRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public boolean doesObjectExist(final String bucketName, final String key) throws OSSException, ClientException {
        return false;
    }

    @Override
    public boolean doesObjectExist(final GenericRequest genericRequest) throws OSSException, ClientException {
        return false;
    }

    @Override
    public boolean doesObjectExist(final String bucketName, final String key, final boolean isOnlyInOSS) {
        return false;
    }

    @Override
    public boolean doesObjectExist(final GenericRequest genericRequest, final boolean isOnlyInOSS)
            throws OSSException, ClientException {
        return false;
    }

    @Override
    public boolean doesObjectExist(final HeadObjectRequest headObjectRequest) throws OSSException, ClientException {
        return false;
    }

    @Override
    public VoidResult setObjectAcl(final String bucketName, final String key, final CannedAccessControlList cannedAcl)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setObjectAcl(final SetObjectAclRequest setObjectAclRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public ObjectAcl getObjectAcl(final String bucketName, final String key) throws OSSException, ClientException {
        return null;
    }

    @Override
    public ObjectAcl getObjectAcl(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public RestoreObjectResult restoreObject(final String bucketName, final String key)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public RestoreObjectResult restoreObject(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public RestoreObjectResult restoreObject(
            final String bucketName,
            final String key,
            final RestoreConfiguration restoreConfiguration
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public RestoreObjectResult restoreObject(final RestoreObjectRequest restoreObjectRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setObjectTagging(final String bucketName, final String key, final Map<String, String> tags)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setObjectTagging(final String bucketName, final String key, final TagSet tagSet)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setObjectTagging(final SetObjectTaggingRequest setObjectTaggingRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public TagSet getObjectTagging(final String bucketName, final String key) throws OSSException, ClientException {
        return null;
    }

    @Override
    public TagSet getObjectTagging(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteObjectTagging(final String bucketName, final String key)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteObjectTagging(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public URL generatePresignedUrl(final String bucketName, final String key, final Date expiration)
            throws ClientException {
        return null;
    }

    @Override
    public URL generatePresignedUrl(
            final String bucketName,
            final String key,
            final Date expiration,
            final HttpMethod method
    )
            throws ClientException {
        return null;
    }

    @Override
    public URL generatePresignedUrl(final GeneratePresignedUrlRequest request) throws ClientException {
        return null;
    }

    @Override
    public VoidResult putBucketImage(final PutBucketImageRequest request) throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketImageResult getBucketImage(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketImageResult getBucketImage(final String bucketName, final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketImage(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketImage(final String bucketName, final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteImageStyle(final String bucketName, final String styleName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteImageStyle(
            final String bucketName,
            final String styleName,
            final GenericRequest genericRequest
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult putImageStyle(final PutImageStyleRequest putImageStyleRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetImageStyleResult getImageStyle(final String bucketName, final String styleName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetImageStyleResult getImageStyle(
            final String bucketName,
            final String styleName,
            final GenericRequest genericRequest
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<Style> listImageStyle(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<Style> listImageStyle(final String bucketName, final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketProcess(final SetBucketProcessRequest setBucketProcessRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketProcess getBucketProcess(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketProcess getBucketProcess(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public InitiateMultipartUploadResult initiateMultipartUpload(final InitiateMultipartUploadRequest request)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public MultipartUploadListing listMultipartUploads(final ListMultipartUploadsRequest request)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public PartListing listParts(final ListPartsRequest request) throws OSSException, ClientException {
        return null;
    }

    @Override
    public UploadPartResult uploadPart(final UploadPartRequest request) throws OSSException, ClientException {
        return null;
    }

    @Override
    public UploadPartCopyResult uploadPartCopy(final UploadPartCopyRequest request)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult abortMultipartUpload(final AbortMultipartUploadRequest request)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public CompleteMultipartUploadResult completeMultipartUpload(final CompleteMultipartUploadRequest request)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketCORS(final SetBucketCORSRequest request) throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<SetBucketCORSRequest.CORSRule> getBucketCORSRules(final String bucketName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<SetBucketCORSRequest.CORSRule> getBucketCORSRules(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public CORSConfiguration getBucketCORS(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketCORSRules(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketCORSRules(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public ResponseMessage optionsObject(final OptionsRequest request) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketLogging(final SetBucketLoggingRequest request) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketLoggingResult getBucketLogging(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketLoggingResult getBucketLogging(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketLogging(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketLogging(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketWebsite(final SetBucketWebsiteRequest setBucketWebSiteRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketWebsiteResult getBucketWebsite(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketWebsiteResult getBucketWebsite(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketWebsite(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketWebsite(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public String generatePostPolicy(final Date expiration, final PolicyConditions conds) throws ClientException {
        return null;
    }

    @Override
    public String calculatePostSignature(final String postPolicy) {
        return null;
    }

    @Override
    public VoidResult setBucketLifecycle(final SetBucketLifecycleRequest setBucketLifecycleRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<LifecycleRule> getBucketLifecycle(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<LifecycleRule> getBucketLifecycle(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketLifecycle(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketLifecycle(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult addBucketReplication(final AddBucketReplicationRequest addBucketReplicationRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<ReplicationRule> getBucketReplication(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<ReplicationRule> getBucketReplication(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketReplication(final String bucketName, final String replicationRuleID)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketReplication(final DeleteBucketReplicationRequest deleteBucketReplicationRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketReplicationProgress getBucketReplicationProgress(
            final String bucketName,
            final String replicationRuleID
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketReplicationProgress getBucketReplicationProgress(
            final GetBucketReplicationProgressRequest getBucketReplicationProgressRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<String> getBucketReplicationLocation(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<String> getBucketReplicationLocation(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public AddBucketCnameResult addBucketCname(final AddBucketCnameRequest addBucketCnameRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<CnameConfiguration> getBucketCname(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<CnameConfiguration> getBucketCname(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketCname(final String bucketName, final String domain)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketCname(final DeleteBucketCnameRequest deleteBucketCnameRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public CreateBucketCnameTokenResult createBucketCnameToken(
            final CreateBucketCnameTokenRequest createBucketCnameTokenRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketCnameTokenResult getBucketCnameToken(final GetBucketCnameTokenRequest getBucketCnameTokenRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketInfo getBucketInfo(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketInfo getBucketInfo(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketStat getBucketStat(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketStat getBucketStat(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketStorageCapacity(final String bucketName, final UserQos userQos)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketStorageCapacity(final SetBucketStorageCapacityRequest setBucketStorageCapacityRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public UserQos getBucketStorageCapacity(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public UserQos getBucketStorageCapacity(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketEncryption(final SetBucketEncryptionRequest setBucketEncryptionRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ServerSideEncryptionConfiguration getBucketEncryption(final String bucketName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ServerSideEncryptionConfiguration getBucketEncryption(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketEncryption(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketEncryption(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketPolicy(final String bucketName, final String policyText)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketPolicy(final SetBucketPolicyRequest setBucketPolicyRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketPolicyResult getBucketPolicy(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketPolicyResult getBucketPolicy(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketPolicy(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketPolicy(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public UploadFileResult uploadFile(final UploadFileRequest uploadFileRequest) {
        return null;
    }

    @Override
    public DownloadFileResult downloadFile(final DownloadFileRequest downloadFileRequest) {
        return null;
    }

    @Override
    public CreateLiveChannelResult createLiveChannel(final CreateLiveChannelRequest createLiveChannelRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setLiveChannelStatus(
            final String bucketName,
            final String liveChannel,
            final LiveChannelStatus status
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setLiveChannelStatus(final SetLiveChannelRequest setLiveChannelRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public LiveChannelInfo getLiveChannelInfo(final String bucketName, final String liveChannel)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public LiveChannelInfo getLiveChannelInfo(final LiveChannelGenericRequest liveChannelGenericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public LiveChannelStat getLiveChannelStat(final String bucketName, final String liveChannel)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public LiveChannelStat getLiveChannelStat(final LiveChannelGenericRequest liveChannelGenericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteLiveChannel(final String bucketName, final String liveChannel)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteLiveChannel(final LiveChannelGenericRequest liveChannelGenericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<LiveChannel> listLiveChannels(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public LiveChannelListing listLiveChannels(final ListLiveChannelsRequest listLiveChannelRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<LiveRecord> getLiveChannelHistory(final String bucketName, final String liveChannel)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<LiveRecord> getLiveChannelHistory(final LiveChannelGenericRequest liveChannelGenericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult generateVodPlaylist(
            final String bucketName,
            final String liveChannelName,
            final String playlistName,
            final long startTime,
            final long endTime
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult generateVodPlaylist(final GenerateVodPlaylistRequest generateVodPlaylistRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public OSSObject getVodPlaylist(
            final String bucketName,
            final String liveChannelName,
            final long startTime,
            final long endTime
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public OSSObject getVodPlaylist(final GetVodPlaylistRequest getVodPlaylistRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public String generateRtmpUri(
            final String bucketName,
            final String liveChannelName,
            final String playlistName,
            final long expires
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public String generateRtmpUri(final GenerateRtmpUriRequest generatePushflowUrlRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult createSymlink(final String bucketName, final String symlink, final String target)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult createSymlink(final CreateSymlinkRequest createSymlinkRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public OSSSymlink getSymlink(final String bucketName, final String symlink) throws OSSException, ClientException {
        return null;
    }

    @Override
    public OSSSymlink getSymlink(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public GenericResult processObject(final ProcessObjectRequest processObjectRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketRequestPayment(final String bucketName, final Payer payer)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketRequestPayment(final SetBucketRequestPaymentRequest setBucketRequestPaymentRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketRequestPaymentResult getBucketRequestPayment(final String bucketName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketRequestPaymentResult getBucketRequestPayment(final GenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketQosInfo(final String bucketName, final BucketQosInfo bucketQosInfo)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketQosInfo(final SetBucketQosInfoRequest setBucketQosInfoRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketQosInfo getBucketQosInfo(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public BucketQosInfo getBucketQosInfo(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketQosInfo(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketQosInfo(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public UserQosInfo getUserQosInfo() throws OSSException, ClientException {
        return null;
    }

    @Override
    public SetAsyncFetchTaskResult setAsyncFetchTask(
            final String bucketName,
            final AsyncFetchTaskConfiguration asyncFetchTaskConfiguration
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public SetAsyncFetchTaskResult setAsyncFetchTask(final SetAsyncFetchTaskRequest setAsyncFetchTaskRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetAsyncFetchTaskResult getAsyncFetchTask(final String bucketName, final String taskId)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetAsyncFetchTaskResult getAsyncFetchTask(final GetAsyncFetchTaskRequest getAsyncFetchTaskRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public CreateVpcipResult createVpcip(final CreateVpcipRequest createVpcipRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<Vpcip> listVpcip() throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteVpcip(final DeleteVpcipRequest deleteVpcipRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult createBucketVpcip(final CreateBucketVpcipRequest createBucketVpcipRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<VpcPolicy> getBucketVpcip(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketVpcip(final DeleteBucketVpcipRequest deleteBucketVpcipRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketInventoryConfiguration(
            final String bucketName,
            final InventoryConfiguration inventoryConfiguration
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketInventoryConfiguration(
            final SetBucketInventoryConfigurationRequest setBucketInventoryConfigurationRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketInventoryConfigurationResult getBucketInventoryConfiguration(
            final String bucketName,
            final String id
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketInventoryConfigurationResult getBucketInventoryConfiguration(
            final GetBucketInventoryConfigurationRequest getBucketInventoryConfigurationRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ListBucketInventoryConfigurationsResult listBucketInventoryConfigurations(final String bucketName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public ListBucketInventoryConfigurationsResult listBucketInventoryConfigurations(
            final String bucketName,
            final String continuationToken
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public ListBucketInventoryConfigurationsResult listBucketInventoryConfigurations(
            final ListBucketInventoryConfigurationsRequest listBucketInventoryConfigurationsRequest
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketInventoryConfiguration(final String bucketName, final String id)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketInventoryConfiguration(
            final DeleteBucketInventoryConfigurationRequest deleteBucketInventoryConfigurationRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public InitiateBucketWormResult initiateBucketWorm(final InitiateBucketWormRequest initiateBucketWormRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public InitiateBucketWormResult initiateBucketWorm(final String bucketName, final int retentionPeriodInDays)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult abortBucketWorm(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult abortBucketWorm(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult completeBucketWorm(final String bucketName, final String wormId)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult completeBucketWorm(final CompleteBucketWormRequest completeBucketWormRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult extendBucketWorm(final String bucketName, final String wormId, final int retentionPeriodInDays)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult extendBucketWorm(final ExtendBucketWormRequest extendBucketWormRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketWormResult getBucketWorm(final String bucketName) throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketWormResult getBucketWorm(final GenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult createDirectory(final String bucketName, final String dirName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult createDirectory(final CreateDirectoryRequest createDirectoryRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public DeleteDirectoryResult deleteDirectory(final String bucketName, final String dirName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public DeleteDirectoryResult deleteDirectory(
            final String bucketName,
            final String dirName,
            final boolean deleteRecursive,
            final String nextDeleteToken
    ) throws OSSException, ClientException {
        return null;
    }

    @Override
    public DeleteDirectoryResult deleteDirectory(final DeleteDirectoryRequest deleteDirectoryRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult renameObject(
            final String bucketName,
            final String sourceObjectName,
            final String destinationObjectName
    )
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult renameObject(final RenameObjectRequest renameObjectRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketResourceGroup(final SetBucketResourceGroupRequest setBucketResourceGroupRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public GetBucketResourceGroupResult getBucketResourceGroup(final String bucketName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult createUdf(final CreateUdfRequest createUdfRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public UdfInfo getUdfInfo(final UdfGenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<UdfInfo> listUdfs() throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteUdf(final UdfGenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult uploadUdfImage(final UploadUdfImageRequest uploadUdfImageRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<UdfImageInfo> getUdfImageInfo(final UdfGenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteUdfImage(final UdfGenericRequest genericRequest) throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult createUdfApplication(final CreateUdfApplicationRequest createUdfApplicationRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public UdfApplicationInfo getUdfApplicationInfo(final UdfGenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public List<UdfApplicationInfo> listUdfApplications() throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteUdfApplication(final UdfGenericRequest genericRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult upgradeUdfApplication(final UpgradeUdfApplicationRequest upgradeUdfApplicationRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult resizeUdfApplication(final ResizeUdfApplicationRequest resizeUdfApplicationRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public UdfApplicationLog getUdfApplicationLog(final GetUdfApplicationLogRequest getUdfApplicationLogRequest)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult setBucketTransferAcceleration(final String bucketName, final boolean enable)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public TransferAcceleration getBucketTransferAcceleration(final String bucketName)
            throws OSSException, ClientException {
        return null;
    }

    @Override
    public VoidResult deleteBucketTransferAcceleration(final String bucketName) throws OSSException, ClientException {
        return null;
    }
}
