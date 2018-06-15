/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: IP 工具类
 * Version: 1.0
 * Date: 18-5-8 下午7:56
 * LastModified: 18-5-8 下午7:56
 */

package com.vphoto.demo.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IpUtils {

    private static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

    private static String serverIp;


    /**
     * 获取requestip
     * @param request
     * @return
     */
    public static String getRequestIpAddress(HttpServletRequest request) {
            if (request == null) return "";
            String ip = request.getHeader("X-Forwarded-For");

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            String p[] = ip.split("[,]", 10);
            if (p.length == 0) {
                return ip;
            }
            if (p.length == 1) {
                return p[0];
            }
            if ("unknown".equalsIgnoreCase(p[0])) {
                return p[1];
            }
            return p[0];
        }

    /**
     * 获取服务器ip
     * @return
     */
    public static String getServerIpAddress(){
        if(null == serverIp){
            synchronized (IpUtils.class){
                if(null == serverIp){
                    serverIp = getIpAddress();
                }
            }
        }
        return serverIp;
    }

    /**
     * 获取mac地址
     * @return
     */
    private static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            byte[] mac = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    mac = netInterface.getHardwareAddress();
                    if (mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        if (sb.length() > 0) {
                            return sb.toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("MAC地址获取失败:"+e.getMessage(), e);
        }
        return "";
    }

    /**
     * 获取ip地址
     * @return
     */
    private static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("IP地址获取失败:"+e.getMessage(), e);
        }
        return "";
    }
}

