import Link from "next/link";

export default function Home() {
  return (
    <div className="min-h-screen bg-gray-100">
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-10">
        <div className="flex flex-col justify-center items-center p-4 sm:h-screen sm:justify-center">
          <h1 className="text-3xl font-bold text-center mb-4 first-letter:text-5xl">Your Goto Digital Wallet.</h1>
          <p className="text-center text-lg mb-12">Zero-fee P2P & zero-fee withdrawals, Send cash from and to your friends and loved ones.</p>
          <Link href={"/auth/login"}>
            <button type="button" className="px-20 py-2 mb-4 bg-gray-900 cursor-pointer rounded-3xl text-white leading-6">Log In</button>
          </Link>
          <h2 className="text-center text-xl">Don&apos;t have an Account? Register <Link href={"/auth/register"}><u className="text-gray-900">Here</u></Link></h2>
        </div>
      </div>
    </div>
  );
}
