#ifdef __OBJC__
#import <UIKit/UIKit.h>
#else
#ifndef FOUNDATION_EXPORT
#if defined(__cplusplus)
#define FOUNDATION_EXPORT extern "C"
#else
#define FOUNDATION_EXPORT extern
#endif
#endif
#endif

#import "AlivcVoiceCallChannelViewController.h"
#import "AlivcIndicatorButton.h"
#import "AlivcVoiceCallChattingViewController.h"
#import "AlivcDismissTransition.h"
#import "AlivcPanInteractiveTransition.h"
#import "AlivcPresentTransition.h"
#import "AlivcVoiceCallSettingViewController.h"
#import "AlivcVideoCallUserAuthrization.h"
#import "AliBaseHttpClient.h"
#import "AliRequestList.h"
#import "NSBundle+AlivcVoiceCallSolo.h"
#import "UIViewController+Alert.h"

FOUNDATION_EXPORT double AlivcVoiceCallSoloVersionNumber;
FOUNDATION_EXPORT const unsigned char AlivcVoiceCallSoloVersionString[];

