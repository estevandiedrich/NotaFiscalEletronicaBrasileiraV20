package br.com.hs.nfe.vo;

public class ZipChecksumVO {
	private byte[] zip;
	private String checksum;
	
	public byte[] getZip() {
		return zip;
	}
	public void setZip(byte[] zip) {
		this.zip = zip;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
}
