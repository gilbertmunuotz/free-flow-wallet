/* eslint-disable @typescript-eslint/no-explicit-any */
import { ModeToggle } from "@/components/theme";
import { Plus, Landmark, Wallet, Banknote } from 'lucide-react';
import { Card, CardContent } from "@/components/ui/card";
import { Tooltip, TooltipContent, TooltipTrigger } from "@/components/ui/tooltip";
import { Dialog, DialogContent, DialogTrigger } from "@/components/ui/dialog";
import Image from "next/image";
import { FundsModal } from "@/components/fundsModal";
import { GetFunds } from "./_action";

const merchants = [
    { name: "CRDB", src: "/CRDB.png" },
    { name: "Airtel Money", src: "/Airtel Money.png" },
    { name: "NMB", src: "/NMB.png" },
];

const getIcon = (provider: string) => {
    switch (provider) {
        case "CRDB": case "NMB":
            return <Landmark className="text-blue-600" size={24} />;
        case "AIRTEL_MONEY": case "TIGOPESA": case "HALOPESA":
            return <Wallet className="text-pink-500" size={24} />;
        default:
            return <Banknote className="text-green-600" size={24} />;
    }
};

export default async function page() {
    const funds = await GetFunds();

    return (
        <div className="flex flex-col min-h-screen bg-white dark:bg-black">
            {/* Topbar */}
            <div className="flex flex-row items-center justify-between border-b pb-2 border-gray-200 dark:border-gray-800">
                <Dialog>
                    <DialogTrigger>
                        <Tooltip>
                            <TooltipTrigger asChild>
                                <div className="ml-3 cursor-pointer">
                                    <Plus size={35} />
                                </div>
                            </TooltipTrigger>
                            <TooltipContent>
                                <p>New P2P</p>
                            </TooltipContent>
                        </Tooltip>
                    </DialogTrigger>
                    <DialogContent>
                        <FundsModal />
                    </DialogContent>
                </Dialog>
                <div className="mr-3">
                    <ModeToggle />
                </div>
            </div>

            {/* Main content */}
            <main className="flex-1 grid grid-cols-1 md:grid-cols-[35%_65%] min-h-[300px]">
                {/* Merchants */}
                <div className="justify-center p-6">
                    <h1 className="text-center text-lg mb-4 font-serif">Available Merchants</h1>
                    <div className="grid gap-4">
                        {merchants.map((merchant) => (
                            <Card key={merchant.name} className="flex items-center justify-center p-4 shadow-md hover:shadow-lg transition bg-white dark:bg-black">
                                <Image
                                    src={merchant.src}
                                    alt={`${merchant.name} Image`}
                                    width={200}
                                    height={150}
                                    className="object-contain"
                                />
                            </Card>
                        ))}
                    </div>
                </div>

                {/* History Section */}
                <div className="items-center justify-center p-6">
                    <h1 className="text-center text-lg font-serif">History</h1>
                    <Card className="bg-white dark:bg-black p-4 space-y-4">
                        {funds.length === 0 ? (
                            <CardContent className="text-center text-xl">No funding history found.</CardContent>
                        ) : (
                            funds.map((fund: any) => (
                                <Card key={fund.id} className="bg-white dark:bg-slate-700 p-4 shadow-md">
                                    <CardContent className="flex justify-between items-center">
                                        <div className="flex items-center space-x-3">
                                            {getIcon(fund.providerName)}
                                            <span className="font-medium text-gray-700 dark:text-white">
                                                {fund.providerName}
                                            </span>
                                        </div>
                                        <span className="text-green-600 dark:text-green-400 font-bold">
                                            + USD {fund.amount}
                                        </span>
                                    </CardContent>
                                </Card>
                            ))
                        )}
                    </Card>
                </div>
            </main>
        </div>
    );
}