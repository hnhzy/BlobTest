package com.hzy.blobtest;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

public class BlobHelper {

    public static CloudBlobContainer getBlobContainer(String containerName) {
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(Constant.storageConnectionString);
            CloudBlobClient serviceClient = account.createCloudBlobClient();

            CloudBlobContainer container = serviceClient.getContainerReference(containerName);

            // Create a permissions object.  
            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();

            // Include public access in the permissions object.  
            containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);

            // Set the permissions on the container.  
            container.uploadPermissions(containerPermissions);
            container.createIfNotExists();

            return container;
        } catch (Exception e) {
            return null;
        }
    }
}  