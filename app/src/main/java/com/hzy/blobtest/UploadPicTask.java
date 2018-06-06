/**
 * Copyright Microsoft Corporation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hzy.blobtest;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

/**
 * 异步上传azure图片
 */
public class UploadPicTask extends AsyncTask<String, Void, Void> {

    private Context mContext;
    private String picPath;
    private String picName;

    public UploadPicTask(Context mContext,String picPath,String picName) {
        this.mContext = mContext;
        this.picPath = picPath;
        this.picName = picName;
    }

    @Override
    protected Void doInBackground(String... arg0) {
        try {
            /**
             * 获取容器，如果没有则创建
             */
            CloudBlobContainer container =BlobHelper.getBlobContainer("testcontainer");
            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
            /**
             * 给容器设置权限
             */
            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
            containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
            container.uploadPermissions(containerPermissions);
            /**
             * blob的名字
             */
            CloudBlockBlob blockBlob = container.getBlockBlobReference(picName);
            blockBlob.uploadFromFile(picPath + picName);
            Toast.makeText(mContext, "上传图片成功--调接口，将地址上传到后台", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(mContext, "上传图片失败--提示用户再次上传", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return null;
    }
}