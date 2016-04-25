package com.tysci.ballq.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PackageUtils
{
    private static PackageUtils instance;

    private PackageUtils(Context c)
    {
        mCanSharePackageNameList = new ArrayList<>();
        mCanShareAppInfo = new ArrayList<>();
        initShareAppInfoData(c);
    }

    public static PackageUtils getInstance(Context c)
    {
        if (instance == null)
        {
            synchronized (PackageUtils.class)
            {
                if (instance == null)
                {
                    instance = new PackageUtils(c);
                }
            }
        }
        return instance;
    }

    private final List<String> mCanSharePackageNameList;
    private final List<AppInfo> mCanShareAppInfo;

    private void initShareAppInfoData(Context c)
    {
        // 获取包管理器 PackageManager
        PackageManager packageManager = c.getPackageManager();
        // 获取ResolverInfo 获取可以分享的应用的List<ResolveInfo>信息
        List<ResolveInfo> mResolveInfo = getShareApps(c);
        if (null != mResolveInfo)
        {
            for (ResolveInfo resolveInfo : mResolveInfo)
            {
                AppInfo appInfo = new AppInfo();
                appInfo.setAppPkgName(resolveInfo.activityInfo.packageName);
                appInfo.setAppLauncherClassName(resolveInfo.activityInfo.name);
                appInfo.setAppName(resolveInfo.loadLabel(packageManager)
                        .toString());
                appInfo.setAppIcon(resolveInfo.loadIcon(packageManager));
                mCanShareAppInfo.add(appInfo);
                mCanSharePackageNameList.add(appInfo.getAppPkgName());
            }
        }
    }

    /**
     * 获取手机中所有的可以分享的ResolveInfo
     */
    private List<ResolveInfo> getShareApps(Context context)
    {
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
        // intent.setType("*/*");
        PackageManager pManager = context.getPackageManager();
        return pManager.queryIntentActivities(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
    }

    public String getVersionCode(Context c)
    {
        PackageManager manager;

        PackageInfo info;

        manager = c.getPackageManager();

        try
        {

            info = manager.getPackageInfo(c.getPackageName(), 0);
            return info.versionName + "";
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<AppInfo> getShareAppInfo()
    {
        return mCanShareAppInfo;
    }

    public List<String> getCanSharePackageNameList()
    {
        return mCanSharePackageNameList;
    }

    private static class AppInfo
    {
        private String appPkgName;
        private String appLauncherClassName;
        private String appName;
        private Drawable appIcon;

        public String getAppPkgName()
        {
            return appPkgName;
        }

        public void setAppPkgName(String appPkgName)
        {
            this.appPkgName = appPkgName;
        }

        public String getAppLauncherClassName()
        {
            return appLauncherClassName;
        }

        public void setAppLauncherClassName(String appLauncherClassName)
        {
            this.appLauncherClassName = appLauncherClassName;
        }

        public String getAppName()
        {
            return appName;
        }

        public void setAppName(String appName)
        {
            this.appName = appName;
        }

        public Drawable getAppIcon()
        {
            return appIcon;
        }

        public void setAppIcon(Drawable appIcon)
        {
            this.appIcon = appIcon;
        }

    }
}
