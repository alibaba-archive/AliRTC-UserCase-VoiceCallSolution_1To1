//
//  AlivcPanInteractiveTransition.h
//  AlivcVoiceCallSolo
//
//  Created by aliyun on 2020/4/15.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface AlivcPanInteractiveTransition : UIPercentDrivenInteractiveTransition
 
@property (nonatomic, assign) BOOL interacting;

- (void)wireToViewController:(UIViewController*)viewController;

@end

NS_ASSUME_NONNULL_END
