package com.dev;

import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;

public class MResourceFactory implements ResourceFactory {
    public Optional<Resource> getResource(Identifier idX) {
        Identifier id = new Identifier(Untitled.getInstance().MOD_ID, idX.getPath());
        //noinspection unused
        return Optional.of(
                new Resource(
                        new DirectoryResourcePack("", Path.of(""), false),
                        () ->
                                getClass()
                                        .getResourceAsStream(
                                                "/assets" + "/" + id.getNamespace() + "/" + id.getPath())) {
                    @Nullable
                    private InputStream stream;

                    public void close() throws IOException {
                        if (stream != null) {
                            stream.close();
                        }
                    }

                    public Identifier getId() {
                        return id;
                    }

                    public InputStream getInputStream() {
                        stream =
                                getClass()
                                        .getResourceAsStream("/assets" + "/" + id.getNamespace() + "/" + id.getPath());
                        return stream;
                    }

                    public boolean hasMetadata() {
                        return false;
                    }

                    @Nullable
                    public <T> T getMetadata(ResourceMetadataReader<T> metaReader) {
                        return null;
                    }

                    public String getResourcePackName() {
                        return id.toString();
                    }
                });
    }
}