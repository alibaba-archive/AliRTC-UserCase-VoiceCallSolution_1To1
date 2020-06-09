//
//  NSBundle+AlivcVoiceCallSolo.h
//  AlivcVoiceCallSolo
//
//  Created by aliyun on 2020/4/21.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSBundle (AlivcVoiceCallSolo)

+ (instancetype)alivc_voiceCallSoloBundle;

+ (UIImage *)imageWithName:(NSString *)name type:(NSString *)type;

+ (UIImage *)pngImageWithName:(NSString *)name;

+ (NSString *)musicPathForResource:(NSString *)name;

@end

NS_ASSUME_NONNULL_END
