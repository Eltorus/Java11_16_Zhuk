package by.tr.xml.Service;

import by.tr.xml.Service.exception.ServiceException;
import by.tr.xml.bean.NodeInfo;

public interface Analyzer {
	NodeInfo next() throws ServiceException;

	void setFile(String filename) throws ServiceException;

	void close() throws ServiceException;
}
