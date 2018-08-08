package com.superwind.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base class for common Request POJO
 *
 * Created by linzhaoming on 16-08-29.
 */
@Data
@NoArgsConstructor
public class CommonReq {
    private PubReqInfo pubInfo;
}
