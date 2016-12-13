package org.ovirtChina.enginePlugin.isoUploaderPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * by fanxu
 */
public class FlowInfo {

    public int      flowChunkSize;
    public long     flowTotalSize;
    public String   flowIdentifier;
    public String   flowFilename;
    public String   flowRelativePath;
    public String   domainName;
    
	public int getFlowChunkSize() {
		return flowChunkSize;
	}
	public void setFlowChunkSize(int flowChunkSize) {
		this.flowChunkSize = flowChunkSize;
	}
	public long getFlowTotalSize() {
		return flowTotalSize;
	}
	public void setFlowTotalSize(long flowTotalSize) {
		this.flowTotalSize = flowTotalSize;
	}
	public String getFlowIdentifier() {
		return flowIdentifier;
	}
	public void setFlowIdentifier(String flowIdentifier) {
		this.flowIdentifier = flowIdentifier;
	}
	public String getFlowFilename() {
		return flowFilename;
	}
	public void setFlowFilename(String flowFilename) {
		this.flowFilename = flowFilename;
	}
	public String getFlowRelativePath() {
		return flowRelativePath;
	}
	public void setFlowRelativePath(String flowRelativePath) {
		this.flowRelativePath = flowRelativePath;
	}

	public String getFlowFilePath() {
		return flowFilePath;
	}
	public void setFlowFilePath(String flowFilePath) {
		this.flowFilePath = flowFilePath;
	}
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public static class FlowChunkNumber {
        public FlowChunkNumber(int number) {
            this.number = number;
        }

        public int number;

        @Override
        public boolean equals(Object obj) {
            return obj instanceof FlowChunkNumber
                    ? ((FlowChunkNumber)obj).number == this.number : false;
        }

        @Override
        public int hashCode() {
            return number;
        }
    }

    //Chunks uploaded
    public HashSet<FlowChunkNumber> uploadedChunks = new HashSet<FlowChunkNumber>();

    public String flowFilePath;

    public boolean vaild(){
        if (flowChunkSize < 0 || flowTotalSize < 0
                || HttpUtils.isEmpty(flowIdentifier)
                || HttpUtils.isEmpty(flowFilename)
                || HttpUtils.isEmpty(flowRelativePath)) {
            return false;
        } else {
            return true;
        }
    }
    public boolean checkIfUploadFinished() {
        //check if upload finished


        int count = (int) Math.ceil(((double) flowTotalSize) / ((double) flowChunkSize));
        for(int i = 1; i < count ; i ++) {

            if (!uploadedChunks.contains(new FlowChunkNumber(i))) {
                return false;
            }
        }

        //Upload finished, change filename.
        File file = new File(flowFilePath);
        String new_path = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - ".temp".length());
        file.renameTo(new File(new_path));
        return true;
    }
}
