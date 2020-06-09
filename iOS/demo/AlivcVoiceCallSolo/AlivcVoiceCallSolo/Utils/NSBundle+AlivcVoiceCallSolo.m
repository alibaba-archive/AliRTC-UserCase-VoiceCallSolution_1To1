//
//  NSBundle+AlivcVoiceCallSolo.m
//  AlivcVoiceCallSolo
//
//  Created by aliyun on 2020/4/21.
//

#import "NSBundle+AlivcVoiceCallSolo.h"
#import "AlivcVoiceCallChannelViewController.h"

@implementation NSBundle (AlivcVoiceCallSolo)

+ (instancetype)alivc_voiceCallSoloBundle {
    static NSBundle *voiceCallSolo = nil;
    if (voiceCallSolo == nil) {
        NSString *bundlePath = [[NSBundle bundleForClass:[AlivcVoiceCallChannelViewController class]] pathForResource:@"AlivcVoiceCallSolo" ofType:@"bundle"];
        voiceCallSolo = [NSBundle bundleWithPath:bundlePath];
    }
    return voiceCallSolo;
}


+ (UIImage *)imageWithName:(NSString *)name type:(NSString *)type{
    int scale = [[UIScreen mainScreen] scale];
    NSString *fullName = [NSString stringWithFormat:@"%@@%dx",name,scale];
    NSString *path =  [[NSBundle alivc_voiceCallSoloBundle] pathForResource:fullName ofType:type];
    return [UIImage imageNamed:path];
}


+ (UIImage *)pngImageWithName:(NSString *)name {
    return [NSBundle imageWithName:name type:@"png"];
}

+ (NSString *)musicPathForResource:(NSString *)name {
    return [[NSBundle alivc_voiceCallSoloBundle] pathForResource:name ofType:@"mp3"];
}
@end
