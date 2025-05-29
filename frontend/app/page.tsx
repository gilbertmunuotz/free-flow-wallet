// import { auth } from "@/auth";
import { ModeToggle } from "@/components/theme";
import Image from "next/image";
import Link from "next/link";
// import { redirect } from "next/navigation";


export default async function Home() {

  // const session = await auth();

  // // Redirect if user is logged in
  // if (session?.accessToken) {
  //   redirect('/home')
  // }

  return (
    <main className="min-h-screen bg-white dark:bg-black">
      <div className="flex flex-row justify-end mr-4 mt-3">
        <ModeToggle />
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-10">

        <div className="relative w-full h-80 sm:h-screen">
          <Image src="/transaction.jpg" alt="Home landing page" fill className="object-contain rounded-2xl" />
        </div>

        <div className="flex flex-col justify-center items-center p-4 sm:h-screen sm:justify-center">
          <h1 className="text-3xl font-semibold text-center mb-4 first-letter:text-4xl">Your Goto Digital Wallet.</h1>
          <p className="text-center text-lg mb-12">Zero-fee P2P & zero-fee withdrawals, Send cash from and to your friends and loved ones.</p>
          <Link href={"/auth/login"}>
            <button type="button" className="px-20 py-2 mb-4 bg-black dark:bg-gray-800 cursor-pointer rounded-3xl text-white leading-6">Log In</button>
          </Link>
          <h2 className="text-center text-xl">Don&apos;t have an Account? Register <Link href={"/auth/register"}><u className="text-black dark:text-white">Here</u></Link></h2>
        </div>
      </div>
    </main>
  );
}
