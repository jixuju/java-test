--
-- Created by IntelliJ IDEA.
-- User: jiangxj
-- Date: 2017/7/15
-- Time: 14:03
-- To change this template use File | Settings | File Templates.
--
local key,limitTime,limit = KEYS[1], tonumber(ARGV[1]), tonumber(ARGV[2])
local value = redis.call('incr', key)
if value > limit then
    return 0
else
    if value == 1 then
        redis.call('pexpire',key,limitTime)
    end
    return 1
end