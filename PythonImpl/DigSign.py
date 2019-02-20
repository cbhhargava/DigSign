import random
safe_prime_513 = 15139927531003959851024276298689406919389244882662000908587914236488545059020631751913371345095583761042796453174928941290719120330180945189107827963522377
g, q = 2, safe_prime_513
def genkeys(n,q,g):
 sk=random.getrandbits(n)
 pk=pow(g,sk,q)
 return sk, pk
def sign(sk,msg,q,g):
 return pow(g,(msg - sk) %(q-1), q)
def verify(pk,sig,msg,q,g):
  print((sig * pk)%q)
  print(pow(g,msg%(q-1),q))
  return (sig * pk) % q == pow(g,msg %(q-1),q)
# Testing
import hashlib
m = hashlib.sha256()
m.update(b"Words are flowing out like endless rain into a paper cup")
hm=int(m.hexdigest(),16)
print("Hashed Message: ", hm)
skx, pkx = genkeys(513,q,2)
print("Keys: ", skx, pkx)
sig = sign(skx,hm,q,2)
print("Sign: ", sig)
print("Verify True: ", verify(pkx,sig,hm,q,2))
print("Verify False: ", verify(pkx,sig,hm+1,q,2))