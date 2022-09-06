package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

public class Function {

    @FunctionName("BlobProcessor")
    public void run(
            @BlobTrigger(name = "file",
                    dataType = "binary",
                    path = "upload/{name}",
                    connection = "BLOB_CONNECTION_STRING") byte[] content,
            @BindingName("name") String filename,
            final ExecutionContext context
    ) {
    	context.getLogger().info("******************************************************************************");
        context.getLogger().info("* NAME: " + filename);
        context.getLogger().info("* SIZE: " + content.length + " bytes");
    	context.getLogger().info("******************************************************************************");
    }
}
