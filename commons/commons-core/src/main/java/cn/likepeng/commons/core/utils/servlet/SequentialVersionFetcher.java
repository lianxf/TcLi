package cn.likepeng.commons.core.utils.servlet;

public class SequentialVersionFetcher implements VersionFetcher {
	private final VersionFetcher[] fetchers;
	
	public SequentialVersionFetcher(VersionFetcher first, VersionFetcher... others) {
		fetchers = new VersionFetcher[others.length + 1];
		fetchers[0] = first;
		for (int i = 0; i < others.length; i++) {
			fetchers[i+1] = others[i]; 
		}
	}

	@Override
	public Version version(String str) {
		for (VersionFetcher fetcher : fetchers) {
			Version version = fetcher.version(str);
			if (version != null) {
				return version;
			}
		}
		return null;
	}

}